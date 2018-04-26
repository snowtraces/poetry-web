package org.xinyo.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chengxinyong on 2018/4/20.
 */
public class WebUtils {
    public static String joinList(List<List<String>> list) {
        List<String> finalList = new ArrayList<>();
        finalList = list.stream()
                .filter(e -> e != null)
                .flatMap(e -> e.stream())
                .collect(Collectors.toList());
        return String.join(",", finalList);
    }

    public static String getDes(String inStr, int len) {
        if (StringUtils.isEmpty(inStr)) {
            return "";
        }
        return inStr.length() > len ? inStr.substring(0, 64).replaceAll("\n", "") + "..."
                : inStr.replaceAll("\n", "");
    }


}
