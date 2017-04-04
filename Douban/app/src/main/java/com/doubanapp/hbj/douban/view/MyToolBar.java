package com.doubanapp.hbj.douban.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.doubanapp.hbj.douban.R;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class MyToolBar extends Toolbar {

    public MyToolBar(Context context) {
        this(context, null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();

        initEvent();
    }

    private void initView() {
        setBackgroundColor(Color.parseColor("#ffffff"));
        setNavigationIcon(R.mipmap.toolbar_back);
        setTitleTextColor(Color.parseColor("#666666"));
        //动画
        /*Animation animation = AnimationUtils.loadAnimation(MyUtils.getContext(), R.anim.my_toolbar_anim);
        startAnimation(animation);*/
    }

    private void initEvent() {
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMyToolbarClick(v);
            }
        });
    }

    public interface onMyNavigationOnClickListener {
        void onMyToolbarClick(View v);
    }

    private onMyNavigationOnClickListener listener;

    public void setOnMyNavigationOnClickListener(onMyNavigationOnClickListener listener) {
        this.listener = listener;
    }
}
