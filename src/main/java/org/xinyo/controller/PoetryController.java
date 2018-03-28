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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @RequestMapping(value = "/poetry/{id}", method = RequestMethod.GET)
    public String getPoetryById(Model model, @PathVariable Integer id) {
        Poetry poetry = poetryService.findPoetryById(id);
        if (poetry == null) {
            return "";
        }
        PoetryBean poetryBean = poetry2PoetryBean(poetry);

        model.addAttribute("poetryBean", poetryBean);

        return "poetryBean";
    }

    @RequestMapping(value = "/poetry/search", method = RequestMethod.GET)
    public String getPoetryByKeyword(Model model, @RequestParam String keyword) {
        keyword = HanLP.convertToSimplifiedChinese(keyword);
        List<Poetry> poetryList = poetryService.findPoetryByKeyword(keyword);
        System.out.println(poetryList);
        if (poetryList == null || poetryList.size() == 0) {
            return null;
        }
        List<PoetryBean> poetryBeanList = poetry2PoetryBean(poetryList);

        model.addAttribute("poetryBeanList", poetryBeanList);
        model.addAttribute("searchKeyword", keyword);

        return "poetryBeanList";
    }

    private PoetryBean poetry2PoetryBean(Poetry poetry) {
        PoetryBean poetryBean = new PoetryBean(poetry);

        // 关键词提取
        List<String> keywordList = HanLP.extractKeyword(poetryBean.getParagraphs(), 10);
        if(keywordList == null || keywordList.size() == 0) {
            poetryBean.setKeyWords("");
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : keywordList) {
                sb.append(s);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            poetryBean.setKeyWords(sb.toString());
        }

        // 文章摘要
        String description;
        if (poetryBean.getParagraphs().length() > 32) {
            description = poetryBean.getParagraphs().substring(0, 32).replaceAll("\n", "");
        } else {
            description = poetryBean.getParagraphs().replaceAll("\n", "");
        }
        poetryBean.setDescription(description);

        // 文章内容
        List<String> contentList = new ArrayList<>();
        if (poetryBean.getParagraphs() != null) {
            String[] split = poetryBean.getParagraphs().split("\n");
            contentList = Arrays.asList(split);
        }
        poetryBean.setContentList(contentList);
        return poetryBean;
    }

    private List<PoetryBean> poetry2PoetryBean(List<Poetry> poetryList) {
        List<PoetryBean> poetryBeanList = new ArrayList<>();
        for (Poetry poetry : poetryList) {
            poetryBeanList.add(poetry2PoetryBean(poetry));
        }
        return poetryBeanList;
    }

    private void emKeyword(List<PoetryBean> poetryBeanList, String keyword){
        for (PoetryBean poetryBean : poetryBeanList) {

        }

    }
}
