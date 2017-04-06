package com.doubanapp.hbj.douban.IPresenter;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public interface IMainPresenter {

    void doAnim();

    void doInitToolBar();

    void doGuidMapCheckResult(Map<String, String> mIsCheckedMap);

    void doInitNavigationBottom();

    void doInitDefaultFragment();

    void doHideFragment();

    void doShowFragment(Toolbar toolbar, int index);
}
