package org.xinyo.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static String getMD5(String str) throws NoSuchAlgorithmException {
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(str.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        return new BigInteger(1, md.digest()).toString(16);
    }


}
