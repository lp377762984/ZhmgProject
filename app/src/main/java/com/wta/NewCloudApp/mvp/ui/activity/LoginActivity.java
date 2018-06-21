package com.wta.NewCloudApp.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.lifecycle.Lifecycleable;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.wta.NewCloudApp.di.component.DaggerLoginComponent;
import com.wta.NewCloudApp.di.module.LoginModule;
import com.wta.NewCloudApp.jiuwei210278.R;
import com.wta.NewCloudApp.mvp.contract.LoginContract;
import com.wta.NewCloudApp.mvp.model.entity.Result;
import com.wta.NewCloudApp.mvp.model.entity.User;
import com.wta.NewCloudApp.mvp.presenter.LoginPresenter;
import com.wta.NewCloudApp.mvp.ui.widget.ClearEditText;
import com.wta.NewCloudApp.uitls.RegexUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends BaseLoadingActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.im_icon)
    ImageView imIcon;
    @BindView(R.id.et_phone)
    ClearEditText etPhone;
    @BindView(R.id.et_code)
    ClearEditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.et_recommend_code)
    ClearEditText etRecommendCode;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.tv_agree)
    TextView tvAgree;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.im_wx_login)
    ImageView imWxLogin;
    private static int CODE_TIME = 30;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public LoginActivity getActivity() {
        return this;
    }

    @OnClick({R.id.tv_get_code, R.id.btn_login, R.id.tv_agree,R.id.im_wx_login})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tv_get_code:
                String s = etPhone.getText().toString();
                if (checkError(s)){
                    etCode.requestFocus();
                    mPresenter.getCode(s);
                }

                break;
            case R.id.btn_login:
                break;
            case R.id.tv_agree:
                WebViewActivity.start(this,"用户协议","http://www.baidu.com");
                break;
            case R.id.im_wx_login:

                break;
        }
    }

    public boolean checkError(String msg) {
        if (msg.length() != 0)
            if (RegexUtils.isMobile(msg)) return true;
            else ArmsUtils.makeText(this.getApplicationContext(), "输入不符合规范，请重新输入");
        else ArmsUtils.makeText(this.getApplicationContext(), "请输入11位手机号码");
        return false;
    }

    @Override
    public void timeCutDown(Result<User> results) {
        ArmsUtils.makeText(this, "短信验证码已发送");
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .compose(RxLifecycleUtils.bindToLifecycle((Lifecycleable) this))
                .subscribeOn(Schedulers.io())
                .take(CODE_TIME, TimeUnit.SECONDS)
                .doOnSubscribe(disposable -> tvGetCode.setEnabled(false))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(aLong -> CODE_TIME - aLong)
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(Long aLong) {
                        tvGetCode.setText(aLong + "s");
                    }

                    @Override
                    public void onError(Throwable e) {
                        tvGetCode.setEnabled(true);
                        tvGetCode.setText("获取验证码");
                    }

                    @Override
                    public void onComplete() {
                        tvGetCode.setEnabled(true);
                        tvGetCode.setText("获取验证码");
                    }
                });
    }
}
