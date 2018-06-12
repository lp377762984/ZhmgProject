package com.wta.NewCloudApp.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.wta.NewCloudApp.mvp.model.entity.Location;
import com.wta.NewCloudApp.mvp.model.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        //String ss1 = "{\"code\":200,\"msg\":\"http://www.bejson.com\",\"data\":{\"street\":\"科技园路.\",\"city\":\"江苏苏州\",\"country\":\"中国\"}}";
        String ss2 = "{\"code\":200,\"msg\":\"哈哈哈哈\",\"data\":[{\"name\":\"Google\",\"url\":\"http://www.google.com\"},{\"name\":\"Baidu\",\"url\":\"http://www.baidu.com\"},{\"name\":\"SoSo\",\"url\":\"http://www.SoSo.com\"}]}";
        //Result result = new Gson().fromJson(ss2, Result.class);
        //List<Baby> babies = fromJsonArray(ss2, Baby.class);
        Result<List<Location>> rl=new Result<>();
        System.out.println();
    }
    public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
        List<T> lst =  new ArrayList<>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }


}
