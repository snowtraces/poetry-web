package org.xinyo.controller;

import com.google.gson.reflect.TypeToken;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xinyo.domain.Author;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
import org.xinyo.domain.SearchResult;
import org.xinyo.service.AuthorService;
import org.xinyo.service.PoetryService;
import org.xinyo.service.SearchResultService;
import org.xinyo.util.JsonUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.xinyo.util.PoetryUtils.poetry2PoetryBean;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@RestController
public class PoetryApiController {

    @Autowired
    private PoetryService poetryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private SearchResultService searchResultService;

    @RequestMapping(value = "/api/poetry/{id}", method = RequestMethod.GET)
    public Map<String, Object> getPoetryById(@PathVariable Integer id, @RequestParam Integer language) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("language", language);

        Poetry poetry = poetryService.findByIdAndLanguage(params);
        PoetryBean poetryBean = poetry2PoetryBean(poetry);

        if(poetry != null && poetry.getAuthorId() != null ){
            params.put("id", poetry.getAuthorId());
            Author author = authorService.findByIdAndLanguage(params);
            resultMap.put("author", author);
        }

        resultMap.put("poetry", poetryBean);
        return resultMap;
    }

    @RequestMapping(value = "/api/poetry/search", method = RequestMethod.GET)
    public Map getPoetryByKeyword(@RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer language) {
        Map<String, Object> resultMap = new HashMap<>();

        if (page == null) {
            page = 1;
        }

        keyword = HanLP.convertToSimplifiedChinese(keyword);
        List<Poetry> poetryList;
        int total = 0;
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", (page - 1) * 10);
        params.put("language", language);

        // 1.读结果表
        SearchResult searchResult = searchResultService.findByKeyword(keyword);

        // 2.根据结果查询
        if (searchResult == null) {
            // 2.1 重新查询poetry表
            total = poetryService.countTotalPoetryByKeyword(params);

            if (total > 0L) {
                poetryList = poetryService.findByKeywordAndLanguage(params);
            } else {
                return null;
            }

            long finalTotal = total;
            String finalKeyword = keyword;
            new Thread(() -> addNewSearchResult(finalKeyword, (int) finalTotal)).start();

        } else {
            // 2.2 根据searchResult表进行查询
            total = searchResult.getTotal();
            if (page > (int) Math.ceil(total / 10d)) return null;

            if (page <= 10) {
                // 只需取索引查询
                String top100Id = searchResult.getTop100Id();
                List<String> list = JsonUtil.jsonToList(top100Id, new TypeToken<ArrayList<String>>() {
                }.getType());
                List<String> idList = list.subList((page - 1) * 10, Math.min(page * 10, total));

                if (language == 0) { // 繁体
                    poetryList = poetryService.findTrByIds(idList);
                } else { // 简体
                    poetryList = poetryService.findSpByIds(idList);
                }
            } else {
                // 重新查询
                poetryList = poetryService.findByKeywordAndLanguage(params);
            }
        }

        List<PoetryBean> poetryBeanList = poetry2PoetryBean(poetryList);

        resultMap.put("poetryBeanList", poetryBeanList);
        resultMap.put("keyword", language == 0 ? HanLP.convertToTraditionalChinese(keyword) : keyword);
        resultMap.put("page", page);
        resultMap.put("total", total);

        return resultMap;
    }

    private void addNewSearchResult(String keyword, int total) {
        SearchResult newResult = new SearchResult();
        newResult.setKeyword(keyword);
        newResult.setTotal(total);
        newResult.setTop100Id(JsonUtil.toJson(poetryService.findTop100IdByKeyword(keyword)));
        searchResultService.add(newResult);
    }

    private void createKeywords(int start, int end){
        Map<String, Object> params = new HashMap<>();
        params.put("language", 1);
        for(int i = start; i <=  end; i ++){
            params.put("id",i);
            Poetry poetry = poetryService.findByIdAndLanguage(params);
            String s = poetry.getTitle() + ", " + poetry.getParagraphs();
            List<String> strings = HanLP.extractKeyword(s, 32);
            List<String> list = new ArrayList<>();
            for (String string : strings) {
                if(string.length() == 2){
                    list.add(string);
                }
            }

            String json = JsonUtil.toJson(list);

            params.put("keywords",json);
            poetryService.updateKeywordsById(params);


            System.err.println(i);

        }

    }


}
