package org.xinyo.controller;

import com.google.gson.reflect.TypeToken;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
import org.xinyo.domain.SearchResult;
import org.xinyo.service.PoetryService;
import org.xinyo.service.SearchResultService;
import org.xinyo.util.JsonUtil;

import java.lang.reflect.Type;
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
    private SearchResultService searchResultService;

    @RequestMapping(value = "/api/poetry/{id}", method = RequestMethod.GET)
    public Poetry getPoetryById(@PathVariable Integer id, @RequestParam Integer language) {
        Poetry poetry;
        if (language == 0) { // 繁体
            poetry = poetryService.findPoetryTrById(id);
        } else { // 简体
            poetry = poetryService.findPoetrySpById(id);
        }
        PoetryBean poetryBean = poetry2PoetryBean(poetry);
        return poetryBean;
    }

    @RequestMapping(value = "/api/poetry/search", method = RequestMethod.GET)
    public Map getPoetryByKeyword(@RequestParam String keyword, @RequestParam Integer page, @RequestParam Integer language) {
        Map<String, Object> resultMap = new HashMap<>();

        if (page == null) {
            page = 1;
        }

        keyword = HanLP.convertToSimplifiedChinese(keyword);
        List<Poetry> poetryList = new ArrayList<>();
        long total = 0L;
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", (page - 1) * 10);

        // 1.读结果表
        SearchResult searchResult = searchResultService.findByKeyword(keyword);

        // 2.根据结果查询
        if (searchResult == null) {
            // 2.1 重新查询poetry表
            total = poetryService.countTotalPoetryByKeyword(params);

            if (total > 0L) {
                if (language == 0) { // 繁体
                    poetryList = poetryService.findPoetryTrByKeyword(params);
                } else { // 简体
                    poetryList = poetryService.findPoetrySpByKeyword(params);
                }
            }
            if (total == 0L || poetryList == null || poetryList.size() == 0) {
                return null;
            }

            long finalTotal = total;
            String finalKeyword = keyword;
            new Thread(() -> addNewSearchResult(finalKeyword, (int) finalTotal)).start();

        } else {
            // 2.2 根据searchResult表进行查询
            total = searchResult.getTotal();

            if (page <= 10) {
                // 只需取索引查询
                String top100Id = searchResult.getTop100Id();
                List<String> list = JsonUtil.jsonToList(top100Id, new TypeToken<ArrayList<String>>() {}.getType());
                List<String> idList = list.subList((page - 1) * 10, page * 10);

                if (language == 0) { // 繁体
                    poetryList = poetryService.findTrByIds(idList);
                } else { // 简体
                    poetryList = poetryService.findSpByIds(idList);
                }
            } else {
                // 重新查询
                if (language == 0) { // 繁体
                    poetryList = poetryService.findPoetryTrByKeyword(params);
                } else { // 简体
                    poetryList = poetryService.findPoetrySpByKeyword(params);
                }
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


}
