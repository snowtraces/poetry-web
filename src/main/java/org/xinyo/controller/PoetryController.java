package org.xinyo.controller;

import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xinyo.domain.Author;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
import org.xinyo.service.AuthorService;
import org.xinyo.service.PoetryService;

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

    @RequestMapping(value = {"/poetry/search"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/poetry/{id}", method = RequestMethod.GET)
    public String getPoetryById(Model model, @PathVariable Integer id,
                                @CookieValue(name = "language", defaultValue = "1") String language) {
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
//    @RequestMapping(value = "/poetry/search", method = RequestMethod.GET)
//    public String getPoetryByKeyword(Model model, @RequestParam String keyword, @RequestParam Integer page) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("keyword", keyword);
//        params.put("page", page);
//        model.addAttribute("poetryParams", params);
//
//        return "poetryList";
//    }

}
