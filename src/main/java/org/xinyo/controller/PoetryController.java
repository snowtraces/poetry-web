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

import java.util.List;

import static org.xinyo.util.PoetryUtils.poetry2PoetryBean;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@Controller
public class PoetryController {
    @Autowired
    private PoetryService poetryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/poetry/{language}/{id}", method = RequestMethod.GET)
    public String getPoetryById(Model model, @PathVariable Integer language, @PathVariable Integer id) {
        Poetry poetry;
        if (language == 0) { // 繁体
            poetry = poetryService.findPoetryTrById(id);
        } else { // 简体
            poetry = poetryService.findPoetrySpById(id);
        }
        PoetryBean poetryBean = poetry2PoetryBean(poetry);
        model.addAttribute("poetryBean", poetryBean);

        return "poetry";
    }

//    @RequestMapping(value = "/poetry/search", method = RequestMethod.GET)
//    public String getPoetryByKeyword(Model model, @RequestParam String keyword) {
//        keyword = HanLP.convertToSimplifiedChinese(keyword);
//        List<Poetry> poetryList = poetryService.findPoetryByKeyword(keyword);
//        System.out.println(poetryList);
//        if (poetryList == null || poetryList.size() == 0) {
//            return null;
//        }
//        List<PoetryBean> poetryBeanList = poetry2PoetryBean(poetryList);
//
//        model.addAttribute("poetryBeanList", poetryBeanList);
//        model.addAttribute("searchKeyword", keyword);
//
//        return "poetryBeanList";
//    }



    private void emKeyword(List<PoetryBean> poetryBeanList, String keyword){
        for (PoetryBean poetryBean : poetryBeanList) {

        }

    }
}
