package org.xinyo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xinyo.domain.*;
import org.xinyo.service.PoetryService;
import org.xinyo.service.SearchResultService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminApiController {
    @Autowired
    private SearchResultService searchResultService;
    @Autowired
    private PoetryService poetryService;

    @RequestMapping(value = "/api/admin/search", method = RequestMethod.GET)
    public Map getPoetryByKeyword(@RequestParam String keyword,
                                  @RequestParam(name = "page", defaultValue = "1") Integer page ) {
        Map<String, Object> resultMap = new HashMap<>();

        page = page == null?1:page;
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", page);

        Map<String, Object> map = searchResultService.adminSearchByKeyword(params);

        resultMap.put("poetryList", map.get("data"));
        resultMap.put("keyword", keyword);
        resultMap.put("page", page);
        resultMap.put("total", map.get("total"));
        return resultMap;
    }
    @RequestMapping(value = "api/admin/edit", method = RequestMethod.GET)
    public Map editPoetry(Integer id) {
        Map<String, Object> resultMap = new HashMap<>();

        FullPoetry poetry = poetryService.findFullPoetryById(id);

        resultMap.put("poetry", poetry);
        return resultMap;
    }



}
