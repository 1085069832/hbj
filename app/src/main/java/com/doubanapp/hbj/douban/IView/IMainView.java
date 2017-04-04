package com.doubanapp.hbj.douban.IView;

import android.view.animation.Animation;

import com.doubanapp.hbj.douban.adapter.MyContentViewPagerAdapter;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public interface IMainView {

    void onInitAnim(Animation animation);

    void onInitToolBar();

    void onGuidMapCheckResult(Map<String, String> mIsCheckedMap);

    void onInitAdapter(MyContentViewPagerAdapter adapter);
}
