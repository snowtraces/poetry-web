package org.xinyo.controller;

import com.google.gson.reflect.TypeToken;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xinyo.domain.Author;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
import org.xinyo.domain.SearchResult;
import org.xinyo.service.AuthorService;
import org.xinyo.service.PoetryService;
import org.xinyo.service.SearchResultService;
import org.xinyo.util.JsonUtils;
import org.xinyo.util.UnicodeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.xinyo.util.PoetryUtils.poetry2PoetryBean;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@Controller
public class PoetryController {
    @Autowired
    private PoetryService poetryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private SearchResultService searchResultService;

//    @RequestMapping(value = {"/poetry/search"}, method = RequestMethod.GET)
//    public String index() {
//        return "index";
//    }

    @RequestMapping(value = "/poetry/{id}", method = RequestMethod.GET)
    public String getPoetryById(Model model, @PathVariable Integer id,
                                @CookieValue(name = "language", defaultValue = "1") Integer language) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("language", language);

        Poetry poetry = poetryService.findByIdAndLanguage(params);

        if (poetry == null) {
            poetry = new Poetry(id);
        }

        PoetryBean poetryBean = poetry2PoetryBean(poetry);
        if (poetry.getAuthorId() != null) {
            params.put("id", poetry.getAuthorId());
            Author author = authorService.findByIdAndLanguage(params);
            model.addAttribute("author", author);
        }

        model.addAttribute("poetry", poetryBean);
        model.addAttribute("language", language);
        return "poetry";
    }
//
    @RequestMapping(value = "/poetry/search", method = RequestMethod.GET)
    public String getPoetryByKeyword(Model model, @RequestParam String keyword,
                                     @RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @CookieValue(name = "language", defaultValue = "1") Integer language) {

        page = page == null?1:page;
        keyword = UnicodeUtils.transBaseUnicode(keyword);
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", page);
        params.put("language", language);

        Map<String, Object> map = searchResultService.searchByKeyword(params);

        List<PoetryBean> poetryBeanList = poetry2PoetryBean((List<Poetry>) map.get("data"));
        keyword = language == 0 ? HanLP.convertToTraditionalChinese(keyword) : keyword;

        int maxLength = 144;
        for (PoetryBean poetryBean : poetryBeanList) {
            String desc = "";
            String contentStr = String.join("", poetryBean.getContentList());
            if(contentStr.length() <= maxLength){
                desc = contentStr;
            } else {
                int index = contentStr.indexOf(keyword);
                if (index == -1) { //没有匹配项
                    desc = contentStr.substring(0, maxLength) + "...";
                } else if (index <= maxLength) { // 初次匹配在maxLength之内
                    desc = contentStr.substring(0, maxLength) + "...";
                } else { // // 初次匹配在maxLength之外
                    if (contentStr.length() > (index + 100)) {
                        desc = "..." + contentStr.substring(index - 20, index + maxLength - 20) + "...";
                    } else {
                        desc = "..." + contentStr.substring(index - 20);
                    }
                }
            }
            poetryBean.setDescription(desc);
        }


        int total = (int) map.get("total");
        model.addAttribute("poetryBeanList", poetryBeanList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", (total % 10) == 0 ? ~~(total / 10) : ~~(total / 10 + 1));
        model.addAttribute("total", total);

        return "poetryList";
    }

}
