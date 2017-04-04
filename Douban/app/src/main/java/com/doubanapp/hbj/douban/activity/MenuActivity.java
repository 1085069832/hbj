package com.doubanapp.hbj.douban.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.fragment.MenuBrowsingHistoryFragment;
import com.doubanapp.hbj.douban.fragment.MenuMyCollectFragment;
import com.doubanapp.hbj.douban.fragment.MenuMyTopicFragment;
import com.doubanapp.hbj.douban.fragment.MenuSettingFragment;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.view.MyToolBar;

public class MenuActivity extends AppCompatActivity implements MyToolBar.onMyNavigationOnClickListener {

    private static final String TAG = "MenuActivity";
    private static int mPos;
    private MyToolBar tb_menu_toolbar;

    public static void startAction(Context context, int pos) {
        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
        mPos = pos;
        MyLogUtils.i(TAG, mPos + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        repalceView();

    }

    private void initView() {
        setContentView(R.layout.activity_menu);
        tb_menu_toolbar = (MyToolBar) findViewById(R.id.tb_menu_toolbar);
        tb_menu_toolbar.setOnMyNavigationOnClickListener(this);
    }

    private void repalceView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (mPos) {
            case 0:
                tb_menu_toolbar.setTitle("我的浏览历史");
                MenuBrowsingHistoryFragment menuBrowsingHistoryFragment = new MenuBrowsingHistoryFragment();
                fragmentTransaction.replace(R.id.fl_menu, menuBrowsingHistoryFragment);
                break;
            case 1:
                tb_menu_toolbar.setTitle("我的收藏");
                MenuMyCollectFragment menuMyCollectFragment = new MenuMyCollectFragment();
                fragmentTransaction.replace(R.id.fl_menu, menuMyCollectFragment);
                break;
            case 2:
                tb_menu_toolbar.setTitle("个性主题");
                MenuMyTopicFragment menuMyTopicFragment = new MenuMyTopicFragment();
                fragmentTransaction.replace(R.id.fl_menu, menuMyTopicFragment);
                break;
            case 3:
                tb_menu_toolbar.setTitle("设置");
                MenuSettingFragment menuSettingFragment = new MenuSettingFragment();
                fragmentTransaction.replace(R.id.fl_menu, menuSettingFragment);
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
