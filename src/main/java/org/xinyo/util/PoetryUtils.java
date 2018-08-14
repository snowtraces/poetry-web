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

    public static List<PoetryBean> poetry2PoetryBean(List<Poetry> poetryList) {
        if (poetryList == null) {
            return null;
        }
        List<PoetryBean> poetryBeanList = new ArrayList<>();
        for (Poetry poetry : poetryList) {
            poetryBeanList.add(new PoetryBean(poetry));
        }
        return poetryBeanList;
    }
}
