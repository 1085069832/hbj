package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doubanapp.hbj.douban.IView.IHomeWelFareFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.presenter.FragmentPresenter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 福利
 * Created by Administrator on 2017/3/31 0031.
 */
public class HomeWelFareFragment extends BaseFragment implements IHomeWelFareFragmentView {

    private static final String TAG = "HomeWelFareFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private MultiTypeAdapter adapter;
    private FragmentPresenter homeWelFareFragmentPresenter;

    public static HomeWelFareFragment newsInstance(int pos) {
        HomeWelFareFragment fragment = new HomeWelFareFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        isFirstCreate = true;
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View initChildView() {
        homeWelFareFragmentPresenter = new FragmentPresenter(mContext, this);
        homeWelFareFragmentPresenter.doRegisterMultitypeItem(rc_base);
        homeWelFareFragmentPresenter.doInitStaggeredGridLayoutManager();


        isCreateView = true;
        lazyLoad();
        return null;
    }

    @Override
    protected synchronized void lazyLoad() {
        MyLogUtils.i(TAG, isCreateView + "isCreateView");
        MyLogUtils.i(TAG, isVisible + "isVisible");
        if (!isFirstCreate || !isCreateView || !isVisible) {
            //不是第一次创建，或者还没有加载完view，或者没显示
            //不加载数据
            return;
        }
        homeWelFareFragmentPresenter.doConnectHttp(MyConstants.HOME_WELFARE_PRESENTER_PAGE_INDEX, false,false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_error:
                homeWelFareFragmentPresenter.doConnectHttp(MyConstants.HOME_WELFARE_PRESENTER_PAGE_INDEX, false,false);
                break;
            default:
        }
    }

    @Override
    public void onFloatingClicked() {
        rc_base.smoothScrollToPosition(0);
    }

    @Override
    public void onRegisterMultitypeItem(MultiTypeAdapter adapter) {
        this.adapter = adapter;
        rc_base.setAdapter(adapter);
    }

    @Override
    public void onInitLayoutManager(RecyclerView.LayoutManager manager) {
        rc_base.setLayoutManager(manager);
    }

    @Override
    public void onStartVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
        isFirstCreate = false;
    }

    @Override
    public void onErrorVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        if (adapter.getItemCount() == 0) {
            rl_error.setVisibility(errorVisb);
        }
    }

    @Override
    public void onRefreshCompleted() {
        //homeWelFareFragmentPresenter.doConnectHttp(MyConstants.HOME_WELFARE_PRESENTER_PAGE_INDEX);
    }

    @Override
    public void onCompletedVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onNotifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homeWelFareFragmentPresenter.doDestroy();
    }
}
