package com.wta.NewCloudApp.mvp.presenter;

import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.jess.arms.utils.ArmsUtils;

import com.wta.NewCloudApp.mvp.model.entity.Result;
import com.wta.NewCloudApp.mvp.model.api.service.HttpResponseHandler;

/**
 * 网络请求的P层
 * @param <M>
 * @param <V>
 */
public class BBasePresenter<M extends IModel,V extends IView> extends BasePresenter<M ,V> implements HttpResponseHandler {
    public BBasePresenter(M model, V rootView) {
        super(model, rootView);
    }

    public BBasePresenter(V rootView) {
        super(rootView);
    }

    public BBasePresenter() {
    }

    @Override
    public void handle10(int what,Result result) {
        ArmsUtils.snackbarText(result.msg);
    }

    @Override
    public void handle11(int what,Result result) {
        ArmsUtils.snackbarText(result.msg);
    }

    @Override
    public void handle20(int what,Result result) {

    }

    @Override
    public <T> void handle200(int what, Result<T> result) {

    }

    @Override
    public <T> void handle404(int what, Result<T> result) {
        ArmsUtils.snackbarText(result.msg);
    }
}
