package com.doubanapp.hbj.douban.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dmax.dialog.SpotsDialog;
import lib.homhomlib.design.SlidingLayout;

import static android.support.v7.widget.RecyclerView.OnFocusChangeListener;

/**
 * BaseFragment
 * Created by Administrator
 * time: 2017-04-04.
 */

public abstract class BaseFragment extends LazyFragment implements View.OnClickListener,
        MainActivity.FloatingClickedListener {

    private static final String TAG = "BaseFragment";
    @BindView(R.id.sr_base)
    SwipeRefreshLayout swipeRLBase;
    private int lastVisibleItemPosition;
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
    @BindView(R.id.sl_base)
    SlidingLayout slBase;

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
        loadingDialog = new SpotsDialog(mContext);//正在加载提示
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
        //设置swipelayout不可用,及监听刷新事件
        swipeRLBase.setEnabled(false);
        swipeRLBase.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseFragment.this.onRefresh();
            }
        });

        rl_error.setOnClickListener(this);
        //rc_base获取到焦点就设置floating监听
        rc_base.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    MyLogUtils.i(TAG, "hasFocus   " + hasFocus);
                    mContext.setFloatingClickedListener(BaseFragment.this);
                    mContext.showFloating();
                    mContext.showBottomNavigation();
                }
            }
        });
        //slidinglayout监听，上拉加载更多
        slBase.setSlidingListener(new SlidingLayout.SlidingListener() {
            private float mDelta = 0;

            @Override
            public void onSlidingOffset(View view, float delta) {
                mDelta = delta;
                MyLogUtils.i(TAG, "delta   " + delta);
            }

            @Override
            public void onSlidingStateChange(View view, int state) {
                //上拉加载更多
                if (state == SlidingLayout.STATE_IDLE && mDelta < 0 && lastVisibleItemPosition == rc_base.getAdapter().getItemCount() - 1) {
                    mContext.hideFloating();
                    mContext.hideBottomNavigation();
                    loadMore();
                }
            }

            @Override
            public void onSlidingChangePointer(View view, int pointerId) {
            }
        });

        //设置Toolbar和floating的显示隐藏，上拉加载更多
        rc_base.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private int mDy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //滑动到最后一个item加载更多
                    if ((lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) && mDy > 0) {
                        loadMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDy = dy;
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                } else {
                    //lastVisibleItemPosition = ((StaggeredGridLayoutManager) ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPositions();
                }

                if (dy > 0) {
                    mContext.hideFloating();
                    mContext.hideBottomNavigation();
                } else if (dy <= 0) {
                    mContext.showFloating();
                    mContext.showBottomNavigation();

                }
            }
        });
    }

    protected abstract View initChildView();//子类添加不同的view,没有则为null

    protected void loadMore() {//子类覆盖此方法上拉加载更多
    }

    protected void onRefresh() {//子类覆盖此方法下拉刷新更多数据
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
