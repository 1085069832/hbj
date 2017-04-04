package com.doubanapp.hbj.douban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.doubanapp.hbj.douban.R;

/*
* 动画界面
* */
public class SpalshActivity extends BaseActivity implements View.OnClickListener {

    private ImageView splash_version;
    private ImageView splash_hello;
    private ImageView splash_dou;
    private Button splash_start;
    private AnimationSet as;
    private AnimationSet as2;
    private AlphaAnimation aa3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initAnimation();

        initEvent();
    }

    private void initView() {
        setContentView(R.layout.activity_spalsh);
        splash_version = (ImageView) findViewById(R.id.splash_version);
        splash_hello = (ImageView) findViewById(R.id.splash_hello);
        splash_dou = (ImageView) findViewById(R.id.splash_dou);
        splash_start = (Button) findViewById(R.id.splash_start);
splash_start.setOnClickListener(this);

    }

    private void initAnimation() {
        as = new AnimationSet(false);
        ScaleAnimation sa = new ScaleAnimation(0f, 1f, 1f, 1f);
        sa.setDuration(1500);
        TranslateAnimation ta = new TranslateAnimation(-100f, 0f, 0f, 0f);
        ta.setDuration(1500);
        as.addAnimation(sa);
        as.addAnimation(ta);
        splash_hello.startAnimation(as);

        as2 = new AnimationSet(false);
        AlphaAnimation aa2 = new AlphaAnimation(0f, 1f);
        aa2.setDuration(500);
        TranslateAnimation ta2 = new TranslateAnimation(0f, 0f, -100f, 0f);
        ta2.setDuration(400);
        as2.addAnimation(aa2);
        as2.addAnimation(ta2);
        as2.addAnimation(ta2);

        aa3 = new AlphaAnimation(0f, 1f);
        aa3.setDuration(700);
    }

    private void initEvent() {
        as.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash_version.setVisibility(View.VISIBLE);
                splash_dou.setVisibility(View.VISIBLE);
                splash_start.setVisibility(View.VISIBLE);
                splash_hello.setVisibility(View.GONE);
                splash_dou.startAnimation(as2);
                splash_start.startAnimation(aa3);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.splash_start:
                Intent intent = new Intent(SpalshActivity.this, GuidActivity.class);
                startActivity(intent);
                finish();
                break;
            default:
        }
    }
}
