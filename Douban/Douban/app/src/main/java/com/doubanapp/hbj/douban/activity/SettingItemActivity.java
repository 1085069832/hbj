package com.doubanapp.hbj.douban.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.fragment.SettingAboutFragment;
import com.doubanapp.hbj.douban.fragment.SettingChangeLanguageFragment;
import com.doubanapp.hbj.douban.fragment.SettingFlowStatisticsFragment;
import com.doubanapp.hbj.douban.fragment.SettingFontSizeFragment;
import com.doubanapp.hbj.douban.fragment.SettingLikeTagFragment;
import com.doubanapp.hbj.douban.fragment.SettingNetDiagnoseFragment;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.view.MyToolBar;

public class SettingItemActivity extends BaseActivity implements MyToolBar.onMyNavigationOnClickListener {

    private static final String TAG = "SettingItemActivity";
    private static int mPos;
    private MyToolBar toolBar;

    public static void startAction(Activity context, int pos) {
        Intent intent = new Intent(context, SettingItemActivity.class);
        context.startActivity(intent);
        mPos = pos;
        MyLogUtils.i(TAG, mPos + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        replaceView();
    }

    private void initView() {
        setContentView(R.layout.activity_setting_item);
        toolBar = (MyToolBar) findViewById(R.id.tb_setting_item);
        toolBar.setOnMyNavigationOnClickListener(this);
    }

    private void replaceView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (mPos) {
            case 0://多语言
                toolBar.setTitle("多语言");
                SettingChangeLanguageFragment settingChangeLanguageFragment = new SettingChangeLanguageFragment();
                fragmentTransaction.replace(R.id.fl_setting_item, settingChangeLanguageFragment);
                break;
            case 1://字体大小
                toolBar.setTitle("字体大小");
                SettingFontSizeFragment settingFontSizeFragment = new SettingFontSizeFragment();
                fragmentTransaction.replace(R.id.fl_setting_item, settingFontSizeFragment);
                break;
            case 2://兴趣标签
                toolBar.setTitle("兴趣标签");
                SettingLikeTagFragment settingLikeTagFragment = new SettingLikeTagFragment();
                fragmentTransaction.replace(R.id.fl_setting_item, settingLikeTagFragment);
                break;
            case 3://网络诊断
                toolBar.setTitle("网络诊断");
                SettingNetDiagnoseFragment settingNetDiagnoseFragment = new SettingNetDiagnoseFragment();
                fragmentTransaction.replace(R.id.fl_setting_item, settingNetDiagnoseFragment);
                break;
            case 4://流量统计
                toolBar.setTitle("流量统计");
                SettingFlowStatisticsFragment settingFlowStatisticsFragment = new SettingFlowStatisticsFragment();
                fragmentTransaction.replace(R.id.fl_setting_item, settingFlowStatisticsFragment);
                break;
            case 6://关于
                toolBar.setTitle("关于");
                SettingAboutFragment settingAboutFragment = new SettingAboutFragment();
                fragmentTransaction.replace(R.id.fl_setting_item, settingAboutFragment);
                break;
            default:
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onMyToolbarClick(View v) {
        finish();
    }
}
