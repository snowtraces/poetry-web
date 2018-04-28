package org.xinyo.util;

import com.google.common.base.Charsets;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by chengxinyong on 2018/3/26.
 */
public class FileUtil {

    public static String read(String fileName) {
        return read(fileName, Charsets.UTF_8);
    }

    public static String read(String fileName, Charset charset) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader isr =
                    new InputStreamReader(new FileInputStream(fileName), charset);
            BufferedReader in = new BufferedReader(isr);
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void writer(File file, String text, boolean append) {
        FileOutputStream fileOutStream = null;
        try {
            fileOutStream = new FileOutputStream(file, append);
            fileOutStream.write((text).getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutStream != null) {
                    fileOutStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void writer(String fileName, String text, boolean append) {
        File file = new File(fileName);
        writer(file, text, append);
    }


}
