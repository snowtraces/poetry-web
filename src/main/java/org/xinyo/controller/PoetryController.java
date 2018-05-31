package org.xinyo.controller;

import com.google.gson.reflect.TypeToken;
import com.hankcs.hanlp.HanLP;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xinyo.domain.*;
import org.xinyo.service.AuthorService;
import org.xinyo.service.PoetryService;
import org.xinyo.service.SearchResultService;
import org.xinyo.util.JsonUtils;
import org.xinyo.util.UnicodeUtils;
import org.xinyo.util.WebUtils;

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

    @Value("${baseweb.description.length}")
    private int descLength;

    @RequestMapping(value = "/poetry/{id}", method = RequestMethod.GET)
    public String getPoetryById(Model model, @PathVariable Integer id,
                                @CookieValue(name = "language", defaultValue = "1") Integer language) {
        BaseWeb baseWeb = new BaseWeb();

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("language", language);

        Poetry poetry = poetryService.findByIdAndLanguage(params);

        if (poetry == null) {
            return "404";
        }

        PoetryBean poetryBean = poetry2PoetryBean(poetry);
        if (poetry.getAuthorId() != null) {
            params.put("id", poetry.getAuthorId());
            Author author = authorService.findByIdAndLanguage(params);
            model.addAttribute("author", author);
        }

        List<List<String>> keywords = new ArrayList<>();

        keywords.add(poetryBean.getTags());
        keywords.add(poetryBean.getKeywords());
        baseWeb.setTitle(poetry.getTitle() + " - " + poetry.getAuthor());
        baseWeb.setKeywords(String.join(",", WebUtils.joinList(keywords)));
        baseWeb.setDescription(WebUtils.getDes(poetry.getParagraphs(), descLength));
        baseWeb.setLanguage(language);

        model.addAttribute("poetry", poetryBean);
        model.addAttribute("web", baseWeb);
        return "poetry";
    }

    @RequestMapping(value = "/poetry/search", method = RequestMethod.GET)
    public String getPoetryByKeyword(Model model, @RequestParam String keyword,
                                     @RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @CookieValue(name = "language", defaultValue = "1") Integer language) {

        BaseWeb baseWeb = new BaseWeb();

        page = page == null ? 1 : page;
        keyword = UnicodeUtils.transBaseUnicode(keyword);
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", page);
        params.put("language", language);

        Map<String, Object> map = searchResultService.searchByKeyword(params);

        List<PoetryBean> poetryBeanList = poetry2PoetryBean((List<Poetry>) map.get("data"));
        keyword = language == 0 ? HanLP.convertToTraditionalChinese(keyword) : keyword;

        int maxLength = 144;
        StringBuilder descSB = new StringBuilder();
        for (PoetryBean poetryBean : poetryBeanList) {
            String desc;
            String contentStr = String.join("", poetryBean.getContentList());
            if (contentStr.length() <= maxLength) {
                desc = contentStr;
            } else {
                int index = contentStr.indexOf(keyword);
                if (index == -1) { //没有匹配项
                    desc = contentStr.substring(0, maxLength) + "...";
                } else if (index <= maxLength) { // 初次匹配在maxLength之内
                    desc = contentStr.substring(0, maxLength) + "...";
                } else { // // 初次匹配在maxLength之外
                    if (contentStr.length() > (index + 120)) {
                        desc = "..." + contentStr.substring(index - 24, index + maxLength - 24) + "...";
                    } else {
                        desc = "..." + contentStr.substring(index - 24);
                    }
                }
            }
            poetryBean.setDescription(desc);

            if(StringUtils.isNoneEmpty(poetryBean.getTitle()) && StringUtils.isNoneEmpty(poetryBean.getAuthor())){
                descSB.append(poetryBean.getTitle());
                descSB.append("[");
                descSB.append(poetryBean.getAuthor());
                descSB.append("]");
                descSB.append(", ");
            }
        }

        baseWeb.setTitle(keyword + " - 搜索结果");
        baseWeb.setDescription("包含[" + keyword + "]" + "的诗词歌赋: " + (descSB.length() > 0 ? descSB.toString().replaceAll(", $",""):""));
        baseWeb.setKeywords(keyword + (descSB.length() > 0 ? ", " + descSB.toString().replaceAll(", $",""):""));
        baseWeb.setLanguage(language);

        model.addAttribute("web", baseWeb);

        int total = (int) map.get("total");
        model.addAttribute("poetryBeanList", poetryBeanList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", (total % 10) == 0 ? ~~(total / 10) : ~~(total / 10 + 1));
        model.addAttribute("total", total);

        if (map.get("relationTag") != null) {
            model.addAttribute("relationTag",map.get("relationTag"));
        }
        return "poetryList";
    }

}
