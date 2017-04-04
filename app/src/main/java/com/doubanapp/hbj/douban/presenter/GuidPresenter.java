package com.doubanapp.hbj.douban.presenter;

import android.view.animation.TranslateAnimation;

import com.doubanapp.hbj.douban.IPresenter.IGuidPresenter;
import com.doubanapp.hbj.douban.activity.GuidActivity;
import com.doubanapp.hbj.douban.IView.IGuidView;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class GuidPresenter implements IGuidPresenter {

    private IGuidView iGuidView;
    private int width = 0;

    public GuidPresenter(IGuidView iGuidView) {
        this.iGuidView = iGuidView;
    }

    @Override
    public void doGetWidth(GuidActivity activity) {
        //屏幕宽度
        width = activity.getWindowManager().getDefaultDisplay().getWidth();
        iGuidView.onWidth(width);
    }

    @Override
    public void doAnim() {
        TranslateAnimation forwardTranslateAnimation = new TranslateAnimation(-width, 0f, 0, 0f);
        TranslateAnimation reverseTranslateAnimation = new TranslateAnimation(width, 0f, 0, 0f);
        forwardTranslateAnimation.setDuration(1000);
        reverseTranslateAnimation.setDuration(1000);
        iGuidView.onInitAnim(forwardTranslateAnimation, reverseTranslateAnimation);
    }

    @Override
    public void doChangeStartHomeState() {
        iGuidView.onChangeStartHomeState();
    }
}
