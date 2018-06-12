package com.wta.NewCloudApp.config;

import com.jess.arms.base.BaseApplication;


public class MyApplication extends BaseApplication {
    private static MyApplication app;

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
    }

    public static MyApplication getInstance() {
        return app;
    }
}
