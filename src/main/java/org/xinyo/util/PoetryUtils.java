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
        if (poetry == null) {
            return null;
        }
        PoetryBean poetryBean = new PoetryBean(poetry);

        // 文章内容
        List<String> contentList = new ArrayList<>();
        if (poetry.getParagraphs() != null) {
            String[] split = poetry.getParagraphs().split("\n");
            contentList = Arrays.asList(split);
        }
        poetryBean.setContentList(contentList);
        return poetryBean;
    }


    public static List<PoetryBean> poetry2PoetryBean(List<Poetry> poetryList) {
        if (poetryList == null) {
            return null;
        }
        List<PoetryBean> poetryBeanList = new ArrayList<>();
        for (Poetry poetry : poetryList) {
            poetryBeanList.add(poetry2PoetryBean(poetry));
        }
        return poetryBeanList;
    }
}
