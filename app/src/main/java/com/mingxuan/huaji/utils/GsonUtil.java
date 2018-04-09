package com.mingxuan.huaji.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/15.
 */
public class GsonUtil {

    public static <T> ArrayList<T> fromJsonList(Gson gson, String json, Class<T> cls) {//这个跟fromJSONDataResult方法同理，只是result为数组getAsJsonArray
        ArrayList<T> mList = new ArrayList<>();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        JsonArray array = object.getAsJsonArray("result");
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

    public static <T> T fromJSONData(Gson gson, String json, Class<T> cls) {
        return gson.fromJson(new JsonParser().parse(json).getAsJsonObject().toString(), cls);
    }

    public static <T> ArrayList<T> fromJSONResult(Gson gson, String json, Class<T> cls,String name) {
        ArrayList<T> mList = new ArrayList<>();
        JsonObject object = new JsonParser().parse(json).getAsJsonObject();
        JsonArray array = object.getAsJsonArray(name);
        for (final JsonElement elem : array) {
            mList.add(gson.fromJson(elem, cls));
        }
        return mList;
    }

}
