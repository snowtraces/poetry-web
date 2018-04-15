package org.xinyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xinyo.domain.Author;
import org.xinyo.domain.DailyPoetry;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
import org.xinyo.service.AuthorService;
import org.xinyo.service.DailyPoetryService;
import org.xinyo.service.PoetryService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.xinyo.util.PoetryUtils.poetry2PoetryBean;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@Controller
public class IndexController {

    @Autowired
    private DailyPoetryService dailyPoetryService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PoetryService poetryService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index(Model model, @CookieValue(name = "language", defaultValue = "1") Integer language) {
        Date day = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dayStr = formatter.format(day);
        DailyPoetry dailyPoetry = dailyPoetryService.selectById(dayStr);

        int poetryId = dailyPoetry == null ? 1391 : dailyPoetry.getPoetryId();

        Map<String, Object> params = new HashMap<>();
        params.put("id", poetryId);
        params.put("language", language);

        Poetry poetry = poetryService.findByIdAndLanguage(params);

        if (poetry == null) {
            poetry = new Poetry(poetryId);
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


}
