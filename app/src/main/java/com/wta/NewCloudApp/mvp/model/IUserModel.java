package com.wta.NewCloudApp.mvp.model;

import com.jess.arms.mvp.IModel;
import com.wta.NewCloudApp.mvp.model.entity.Result;
import com.wta.NewCloudApp.mvp.model.entity.User;

import java.io.File;

import io.reactivex.Observable;

public interface IUserModel extends IModel {
    Observable<Result<User>> sendCode(String phone);//发送验证码
    Observable<Result<User>> login(String phone, String code);//登陆
    //修改用户信息
    Observable<Result<User>> setName(String name);
    //上传用户头像
    Observable<Result<User>> setPortrait(File head);
}
