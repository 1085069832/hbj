package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;

/**
 * BaseFragment
 * Created by Administrator
 * time: 2017-04-04.
 */

public abstract class BaseFragment extends LazyFragment implements View.OnClickListener, MainActivity.FloatingClickedListener {

    protected RecyclerView rc_base;
    protected ProgressBar pb_loading;
    protected RelativeLayout rl_error;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置监听
        mContext.setFloatingClickedListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RelativeLayout relativeLayout = new RelativeLayout(MyUtils.getContext());
        View view = inflater.inflate(R.layout.fg_base, container, false);
        rc_base = (RecyclerView) view.findViewById(R.id.rc_base);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        rl_error = (RelativeLayout) view.findViewById(R.id.rl_error);
        relativeLayout.addView(view);

        if (initChildView() != null)
            relativeLayout.addView(initChildView());
        return relativeLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rl_error.setOnClickListener(this);
    }

    /*
        * 隐藏状态改变重新设置floating监听*/
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mContext.setFloatingClickedListener(this);
        }
    }

    protected abstract View initChildView();//子类添加不同的view,没有则为null
}
