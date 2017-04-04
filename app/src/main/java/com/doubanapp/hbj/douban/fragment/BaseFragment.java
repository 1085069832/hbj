package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.utils.MyUtils;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public abstract class BaseFragment extends LazyFragment {

    protected RecyclerView rc_base;
    protected ProgressBar pb_loading;
    protected TextView tv_error;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RelativeLayout relativeLayout = new RelativeLayout(MyUtils.getContext());
        View view = inflater.inflate(R.layout.fg_base, container, false);
        rc_base = (RecyclerView) view.findViewById(R.id.rc_base);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        tv_error = (TextView) view.findViewById(R.id.tv_error);
        relativeLayout.addView(view);

        if (initChildView() != null)
            relativeLayout.addView(initChildView());

        return relativeLayout;
    }

    protected abstract View initChildView();//子类添加不同的view,没有则为null
}
