package com.wta.NewCloudApp.config;

import com.jess.arms.base.BaseApplication;


public class App extends BaseApplication {
    private static App app;

    @Override
    public void onCreate() {
        app = this;
        super.onCreate();
    }

    public static App getInstance() {
        return app;
    }
}
