package com.doubanapp.hbj.douban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;

import com.doubanapp.hbj.douban.IView.ISpalshView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.presenter.SpalshPresenter;

/*
* 动画界面
* */
public class SpalshActivity extends BaseActivity implements View.OnClickListener, ISpalshView {

    private ImageView splash_version;
    private ImageView splash_hello;
    private ImageView splash_dou;
    private Button splash_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        splash_version = (ImageView) findViewById(R.id.splash_version);
        splash_hello = (ImageView) findViewById(R.id.splash_hello);
        splash_dou = (ImageView) findViewById(R.id.splash_dou);
        splash_start = (Button) findViewById(R.id.splash_start);
        splash_start.setOnClickListener(this);

        SpalshPresenter spalshPresenter = new SpalshPresenter(this);
        spalshPresenter.doAnim();

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

    @Override
    public void onInitAnim(AnimationSet as1, final AnimationSet as2, final Animation a) {
        splash_hello.startAnimation(as1);
        as1.setAnimationListener(new Animation.AnimationListener() {
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
                splash_start.startAnimation(a);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
