package com.wta.NewCloudApp.di.module;

import com.jess.arms.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

import com.wta.NewCloudApp.mvp.contract.SideContract;
import com.wta.NewCloudApp.mvp.model.SideModel;


@Module
public class SideModule {
    private SideContract.View view;

    /**
     * 构建SideModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public SideModule(SideContract.View view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    SideContract.View provideSideView() {
        return this.view;
    }

    @FragmentScope
    @Provides
    SideContract.Model provideSideModel(SideModel model) {
        return model;
    }
}