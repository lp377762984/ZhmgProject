package com.wta.NewCloudApp.mvp.model.api.service;

import com.wta.NewCloudApp.mvp.model.entity.LoginEntity;
import com.wta.NewCloudApp.mvp.model.entity.Result;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

public interface CommonCache {
    @LifeCache(duration = 5,timeUnit = TimeUnit.MINUTES)
    Observable<Reply<Result<LoginEntity>>> getUsers(Observable<Result<LoginEntity>> loginEntity, DynamicKey idLastUserQueried, EvictProvider evictProvider);
}
