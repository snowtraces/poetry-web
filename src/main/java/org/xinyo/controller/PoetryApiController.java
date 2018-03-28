package org.xinyo.controller;

import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;
import org.xinyo.service.PoetryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chengxinyong on 2018/3/27.
 */
@RestController
public class PoetryApiController {
    @Autowired
    private PoetryService poetryService;

    @RequestMapping(value = "/api/poetry/{id}", method = RequestMethod.GET)
    public Poetry getPoetryById(@PathVariable Integer id){
        Poetry poetry = poetryService.findPoetryById(id);
        PoetryBean poetryBean = poetry2PoetryBean(poetry);
        return poetryBean;
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


}
