package com.wta.NewCloudApp.mvp.presenter;


import android.app.Activity;
import android.content.Intent;

import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.wta.NewCloudApp.config.App;
import com.wta.NewCloudApp.config.DefaultHandleSubscriber;
import com.wta.NewCloudApp.config.HttpResponseHandler;
import com.wta.NewCloudApp.mvp.model.entity.Result;
import com.wta.NewCloudApp.mvp.ui.activity.LoginActivity;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;


public class BBasePresenter<M extends IModel, V extends IView> extends BasePresenter<M, V> implements HttpResponseHandler {
    @Inject
    RxErrorHandler mErrorHandler;

    public BBasePresenter(M model, V rootView) {
        super(model, rootView);
    }

    @Override
    public void handle10(int what, Result result) {
        ArmsUtils.snackbarText(result.msg);
    }

    @Override
    public void handle11(int what, Result result) {
        ArmsUtils.snackbarText(result.msg);
    }

    @Override
    public void handle20(int what, Result result) {
        Activity topActivity = App.getInstance().getAppComponent().appManager().getTopActivity();
        Intent intent = new Intent(topActivity, LoginActivity.class);
        topActivity.startActivity(intent);
    }

    @Override
    public <T> void handle200(int what, Result<T> result) {

    }

    @Override
    public <T> void handle404(int what, Result<T> result) {
        ArmsUtils.snackbarText(result.msg);
    }

    //执行网络请求
    protected <T> void doRequest(Observable<T> observable, RxErrorHandler errorHandler, int what, HttpResponseHandler handler) {
        observable.subscribe(new DefaultHandleSubscriber<T>(errorHandler, what, handler));
    }

    //执行网络请求
    protected <T> void doRequest(Observable<T> observable, RxErrorHandler errorHandler, int what) {
        doRequest(observable, errorHandler, what, this);
    }

    protected <T> void doRequest(Observable<T> observable, int what) {
        doRequest(observable, mErrorHandler, what, this);
    }


    //生成网络请求
    protected <T> Observable<T> buildRequest(Observable<T> observable, boolean needLoading) {
        return observable
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    if (needLoading) mRootView.showLoading();
                })
                .subscribeOn(AndroidSchedulers.mainThread())//
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (needLoading) mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView));
    }

    //生成网络请求
    protected <T> Observable<T> buildRequest(Observable<T> observable) {
        return buildRequest(observable, true);
    }

}
