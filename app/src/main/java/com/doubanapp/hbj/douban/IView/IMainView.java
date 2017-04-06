package com.doubanapp.hbj.douban.IView;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.Animation;

import com.doubanapp.hbj.douban.adapter.MyContentViewPagerAdapter;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public interface IMainView {

    void onInitAnim(Animation animation);

    void onInitToolBar();

    void onGuidMapCheckResult(Map<String, String> mIsCheckedMap);

    void onInitNavigationBottom(List<BottomNavigationItem> bottomNavigationItemsList);

    void onNavigationDefaultShow(int defaultSelect);
}
