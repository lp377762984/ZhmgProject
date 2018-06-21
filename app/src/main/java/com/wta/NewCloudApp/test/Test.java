package com.wta.NewCloudApp.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.wta.NewCloudApp.mvp.model.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

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
