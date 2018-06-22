package com.wta.NewCloudApp.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.wta.NewCloudApp.jiuwei210278.R;
import com.wta.NewCloudApp.mvp.ui.fragment.HomeFragment;
import com.wta.NewCloudApp.mvp.ui.fragment.MineFragment;
import com.wta.NewCloudApp.mvp.ui.fragment.SideFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabLayout.setTabData(createTabData(), this, R.id.frameLayout, createFragments());
    }

    private ArrayList<Fragment> createFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>(3);
        fragments.add(new HomeFragment());
        fragments.add(new SideFragment());
        fragments.add(new MineFragment());
        return fragments;
    }

    private ArrayList<CustomTabEntity> createTabData() {
        ArrayList<CustomTabEntity> customTabEntities=new ArrayList<>(3);
        customTabEntities.add(new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return "首页";
            }

            @Override
            public int getTabSelectedIcon() {
                return R.mipmap.tab_home_select;
            }

            @Override
            public int getTabUnselectedIcon() {
                return R.mipmap.tab_home_unselect;
            }
        });
        customTabEntities.add(new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return "周边";
            }

            @Override
            public int getTabSelectedIcon() {
                return R.mipmap.tab_side_select;
            }

            @Override
            public int getTabUnselectedIcon() {
                return R.mipmap.tab_side_unselect;
            }
        });
        customTabEntities.add(new CustomTabEntity() {
            @Override
            public String getTabTitle() {
                return "我的";
            }

            @Override
            public int getTabSelectedIcon() {
                return R.mipmap.tab_mine_select;
            }

            @Override
            public int getTabUnselectedIcon() {
                return R.mipmap.tab_mine_unselect;
            }
        });
        return customTabEntities;
    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }
}
