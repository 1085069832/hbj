package com.doubanapp.hbj.douban.presenter;

import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.doubanapp.hbj.douban.IView.ISpalshView;
import com.doubanapp.hbj.douban.IPresenter.ISpalshPresenter;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class SpalshPresenter implements ISpalshPresenter {

    private ISpalshView iSpalshView;

    public SpalshPresenter(ISpalshView iSpalshView) {
        this.iSpalshView = iSpalshView;
    }

    @Override
    public void doAnim() {
        AnimationSet as = new AnimationSet(false);
        ScaleAnimation sa = new ScaleAnimation(0f, 1f, 1f, 1f);
        sa.setDuration(1500);
        TranslateAnimation ta = new TranslateAnimation(-100f, 0f, 0f, 0f);
        ta.setDuration(1500);
        as.addAnimation(sa);
        as.addAnimation(ta);

        AnimationSet as2 = new AnimationSet(false);
        AlphaAnimation aa2 = new AlphaAnimation(0f, 1f);
        aa2.setDuration(500);
        TranslateAnimation ta2 = new TranslateAnimation(0f, 0f, -100f, 0f);
        ta2.setDuration(400);
        as2.addAnimation(aa2);
        as2.addAnimation(ta2);
        as2.addAnimation(ta2);

        AlphaAnimation aa3 = new AlphaAnimation(0f, 1f);
        aa3.setDuration(700);

        iSpalshView.onInitAnim(as, as2, aa3);
    }
}
