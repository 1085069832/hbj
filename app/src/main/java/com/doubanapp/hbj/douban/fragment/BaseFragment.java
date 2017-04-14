package com.doubanapp.hbj.douban.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;

import static android.support.v7.widget.RecyclerView.OnFocusChangeListener;

/**
 * BaseFragment
 * Created by Administrator
 * time: 2017-04-04.
 */

public abstract class BaseFragment extends LazyFragment implements View.OnClickListener,
        MainActivity.FloatingClickedListener {

    private static final String TAG = "BaseFragment";
    @BindView(R.id.rc_base)
    RecyclerView rc_base;
    @BindView(R.id.pb_loading)
    ProgressWheel pb_loading;
    @BindView(R.id.rl_error)
    RelativeLayout rl_error;
    Unbinder unbinder;
    protected AlertDialog loadingDialog;
    @BindView(R.id.rl)
    RelativeLayout rl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建就设置Floating监听
        mContext.setFloatingClickedListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout relativeLayout = new RelativeLayout(MyUtils.getContext());
        View view = inflater.inflate(R.layout.fg_base, container, false);
        loadingDialog = new SpotsDialog(mContext);
        loadingDialog.setCancelable(false);
        unbinder = ButterKnife.bind(this, view);
        relativeLayout.addView(view);
        if (initChildView() != null)
            relativeLayout.addView(initChildView());
        return relativeLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rl_error.setOnClickListener(this);
        //rc_base获取到焦点就设置floating监听
        rc_base.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    mContext.setFloatingClickedListener(BaseFragment.this);
            }
        });
        //设置floating的显示隐藏
        rc_base.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mContext.showFloating();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 5) {
                    mContext.hideFloating();
                } else if (dy < 0) {
                    mContext.showFloating();
                }
            }
        });

    }

    protected abstract View initChildView();//子类添加不同的view,没有则为null

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
