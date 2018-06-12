package com.wta.NewCloudApp.config;

import com.wta.NewCloudApp.mvp.model.entity.Result;
import com.wta.NewCloudApp.mvp.model.api.service.HttpResponseHandler;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

public class DefaultHandleSubscriber<K> extends ErrorHandleSubscriber<K> {
    private HttpResponseHandler handler;
    private int what = -2018;

    public DefaultHandleSubscriber(RxErrorHandler rxErrorHandler) {
        super(rxErrorHandler);
    }

    public int getWhat() {
        return what;
    }

    public DefaultHandleSubscriber<K> setWhat(int what) {
        this.what = what;
        return this;
    }

    public HttpResponseHandler getHandler() {
        return handler;
    }

    public DefaultHandleSubscriber<K> setHandler(HttpResponseHandler handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public void onNext(K k) {
        if (k instanceof Result) {
            Result rt = (Result) k;
            switch (rt.code) {
                case 10:
                    if (handler != null) handler.handle10(what,rt);
                    break;
                case 11:
                    if (handler != null) handler.handle11(what, rt);
                    break;
                case 20:
                    if (handler != null) handler.handle20(what, rt);
                    break;
                case 200:
                    if (handler != null) handler.handle200(what, rt);
                    break;
                case 404:
                    if (handler != null) handler.handle404(what, rt);
                    break;
            }
        }
    }
}
