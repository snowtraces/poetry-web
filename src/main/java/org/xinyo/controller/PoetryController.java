package org.xinyo.controller;

import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
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

    @RequestMapping(value = {"/", "/poetry/{id}", "/poetry/search" }, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

//    @RequestMapping(value = "/poetry/{id}", method = RequestMethod.GET)
//    public String getPoetryById(Model model, @PathVariable Integer id) {
//        model.addAttribute("poetryId", id);
//        return "poetry";
//    }
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
