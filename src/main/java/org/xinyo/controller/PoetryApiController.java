package org.xinyo.controller;

import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
@RestController
public class PoetryApiController {
    @Autowired
    private PoetryService poetryService;

    @RequestMapping(value = "/api/poetry/{id}", method = RequestMethod.GET)
    public Poetry getPoetryById(@PathVariable Integer id) {
        Poetry poetry = poetryService.findPoetryById(id);
        PoetryBean poetryBean = poetry2PoetryBean(poetry);
        return poetryBean;
    }

    @RequestMapping(value = "/api/poetry/search", method = RequestMethod.GET)
    public Map getPoetryByKeyword(@RequestParam String keyword, @RequestParam Integer page) {
        Map<String, Object> resultMap = new HashMap<>();

        if (page == null) {
            page = 1;
        }
        keyword = HanLP.convertToSimplifiedChinese(keyword);
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("page", (page - 1)*10);
        List<Poetry> poetryList = poetryService.findPoetryByKeyword(params);

        System.out.println(poetryList);
        if (poetryList == null || poetryList.size() == 0) {
            return null;
        }
        List<PoetryBean> poetryBeanList = poetry2PoetryBean(poetryList);

        resultMap.put("poetryBeanList", poetryBeanList);
        resultMap.put("keyword_sp", keyword);
        resultMap.put("keyword_tr", HanLP.convertToTraditionalChinese(keyword));
        resultMap.put("page", page);

        return resultMap;
    }


}
