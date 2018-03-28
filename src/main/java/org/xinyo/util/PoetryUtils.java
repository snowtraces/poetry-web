package org.xinyo.util;

import com.hankcs.hanlp.HanLP;
import org.xinyo.domain.Poetry;
import org.xinyo.domain.PoetryBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CHENG on 2018/3/28.
 */
public class PoetryUtils {

    public static PoetryBean poetry2PoetryBean(Poetry poetry) {
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


    public static List<PoetryBean> poetry2PoetryBean(List<Poetry> poetryList) {
        List<PoetryBean> poetryBeanList = new ArrayList<>();
        for (Poetry poetry : poetryList) {
            poetryBeanList.add(poetry2PoetryBean(poetry));
        }
        return poetryBeanList;
    }
}
