package org.xinyo.util;

import com.hankcs.hanlp.HanLP;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unicode 和 String 的相互转换，并保留ASCII/Unicode部分
 *
 * @author CHENG
 */
public class UnicodeUtils {

    /**
     * Unicode转string
     *
     * @param unicode
     * @return
     */
    public static String fromUnicode(String unicode) {
        // 其中\\\\u\\w{4}为匹配unicode，后面用X代替；((?!X).)*为否定向后（右）环视匹配，匹配不包含X的内容；((?!X).)*+中的加号为占有优先，放弃后续备用路径，提高正则表达式的效率
        Pattern pattern = Pattern.compile("(((?!\\\\u\\w{4}).)*+)(\\\\u(\\w{4}+))?(((?!\\\\u\\w{4}).)*+)");
        Matcher matcher = pattern.matcher(unicode);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            buffer.append(matcher.group(1));
            if (matcher.group(4) != null) {
                char letter = (char) Integer.parseInt(matcher.group(4), 16);
                buffer.append(letter);
            }
            buffer.append(matcher.group(5));
        }
        return buffer.toString();
    }

    /**
     * String转Unicode
     *
     * @param string
     * @return
     */
    public static String toUnicode(String string) {
        // 其中[\\u0000-\\u007F]为ASCII码十六进制（Unicode）范围
        Pattern pattern = Pattern.compile("([\\u0000-\\u007F]*+)([^\\u0000-\\u007F])?([\\u0000-\\u007F]*+)");
        Matcher matcher = pattern.matcher(string);
        StringBuffer buffer = new StringBuffer();
        while (matcher.find()) {
            buffer.append(matcher.group(1));
            if (matcher.group(2) != null) {
                buffer.append(escapeNonAscii(matcher.group(2)));
            }
            buffer.append(matcher.group(3));
        }
        return buffer.toString();
    }

    /**
     * 查询是否为拓展unicode
     *
     * @return
     */
    public static String transBaseUnicode(String inStr) {
        int index = 0;
        String spStr = HanLP.convertToSimplifiedChinese(inStr);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < spStr.length(); i++) {
            int cp = spStr.codePointAt(i);
            if (Character.isSupplementaryCodePoint(cp)) {
                sb.append(spStr.substring(index, i));
                sb.append(inStr.substring(i, i + 1));
                index += 2;
                i++;
            } else {
                sb.append(spStr.substring(i, i+1));
                index++;
            }
        }
        System.err.println(sb);
        return sb.toString();
    }

    /**
     * 非ASCII转Unicode
     * 来源：{@link https://stackoverflow.com/questions/5733931/java-string-unicode-value/14817912#14817912}
     *
     * @param str
     * @return
     */
    private static String escapeNonAscii(String str) {
        StringBuilder retStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int cp = Character.codePointAt(str, i);
            int charCount = Character.charCount(cp);
            if (charCount > 1) {
                i += charCount - 1; // 2.
                if (i >= str.length()) {
                    throw new IllegalArgumentException("truncated unexpectedly");
                }
            }
            if (cp < 128) {
                retStr.appendCodePoint(cp);
            } else {
                retStr.append(String.format("\\u%x", cp));
            }
        }
        return retStr.toString();
    }

    @Test
    public void testFromUnicode() {
        String string = UnicodeUtils.fromUnicode("unicode \\u548c string \\u7684\\u76f8\\u4e92\\u8f6c\\u6362\\uff0c\\u5e76\\u4fdd\\u7559\\u975e\\u4e2d\\u6587/unicode\\u90e8\\u5206");
        System.err.println(string);
    }

    @Test
    public void testToUnicode() throws UnsupportedEncodingException {
        String unicode = UnicodeUtils.toUnicode("于\uD84F\uDE23");
        System.err.println(unicode);
    }
}