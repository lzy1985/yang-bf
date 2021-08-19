package com.yang.study.base.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;

/**
 * @author lzy
 * @Version 1.0.0
 * @Date 2020/11/6 15:38
 * @Description json工具类
 */
public class JsonUtil {
    private static Gson gson;

    static {
        GsonBuilder builder = new GsonBuilder();
        //自定义String适配器
        builder.registerTypeAdapter(String.class, new TypeAdapter<String>() {
            public String read(JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return "";
                }
                return reader.nextString();
            }

            public void write(JsonWriter writer, String value) throws IOException {
                if (value == null) {
                    // 在这里处理null改为空字符串
                    writer.value("");
                    return;
                }
                writer.value(value);
            }
        });
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        //
        gson = builder.serializeNulls().create();
    }

    /**
     * 将对象转为json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        return JSON.toJSONString(obj);
    }


    /**
     * 将对象转为json，不进行html字符转换
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJsonWithoutHtmlEscaping(T obj) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(obj);
    }

    /**
     * FastJson 将Json转为对象
     *
     * @param jsonData
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonData, Class<T> type) {
        if (null == jsonData) {
            return null;
        }
        return JSON.parseObject(jsonData, type);
    }

    /**
     * 根据范性转换对象
     * @param jsonData
     * @param typeToken
     * @param <T>
     * @return
     */
    public static <T> T toObject(String jsonData, TypeToken typeToken) {
        if (null == jsonData) {
            return null;
        }
        return JsonUtil.getGSon().fromJson(jsonData, typeToken.getType());
    }

    /**
     * Gson 将json转为list
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     * @Description 例如：List<User> list = JsonUtil.toList(str1,new TypeToken<List<User>>(){});
     */
    public static <T> List<T> toList(String json, TypeToken type) {
        return gson.fromJson(json, type.getType());
    }

    /**
     * FastJson 将json转为list
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     * @Description 例如：List<User> list = JsonUtil.toList(str1,new TypeToken<List<User>>(){});
     */
    public static <T> List<T> toList(String json, Class<T> type) {
        return JSON.parseArray(json, type);
    }

    public static Gson getGSon(){
        return gson;
    }
}
