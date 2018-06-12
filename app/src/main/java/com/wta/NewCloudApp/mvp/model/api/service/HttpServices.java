package com.wta.NewCloudApp.mvp.model.api.service;


import com.wta.NewCloudApp.mvp.model.entity.LoginEntity;
import com.wta.NewCloudApp.mvp.model.entity.QustionAll;
import com.wta.NewCloudApp.mvp.model.entity.Result;
import com.wta.NewCloudApp.mvp.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HttpServices {
    @FormUrlEncoded
    @POST("/getLoginVerify")
    Observable<Result<User>> sendCode(@Field("mobile") String phone);
    @FormUrlEncoded
    @POST("/login")
    Observable<Result<LoginEntity>> login(@Field("mobile") String phone, @Field("verify") String code);
    @FormUrlEncoded
    @POST("/question/omnibus")
    Observable<QustionAll> getRecommends(@Field("questionId") Long questionId);
}
