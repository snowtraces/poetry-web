package org.xinyo.util;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by chengxinyong on 2018/3/30.
 */
public class JsonUtil {
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static List jsonToList(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

}
