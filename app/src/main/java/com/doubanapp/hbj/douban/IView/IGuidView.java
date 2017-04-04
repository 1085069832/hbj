package com.doubanapp.hbj.douban.IView;

import android.view.animation.TranslateAnimation;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public interface IGuidView {
    //屏幕宽度
    void onWidth(int width);

    //初始化动画
    void onInitAnim(TranslateAnimation forwardTranslateAnimation, TranslateAnimation reverseTranslateAnimation);

    //开启状态
    void onChangeStartHomeState();
}
