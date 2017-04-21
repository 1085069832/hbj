package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doubanapp.hbj.douban.IView.IMovieFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.presenter.FragmentPresenter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 电影
 * Created by Administrator on 2017/3/17 0017.
 */
public class MovieFragment extends BaseFragment implements IMovieFragmentView {

    private static final String TAG = "MovieFragment";
    private FragmentPresenter movieFragmentPresenter;
    private MultiTypeAdapter adapter;


    public static MovieFragment newsInstance(int pos) {
        MovieFragment fragment = new MovieFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initChildView() {
        MyLogUtils.i(TAG, "initChildView");
        movieFragmentPresenter = new FragmentPresenter(mContext, this);
        movieFragmentPresenter.doRegisterMultitypeItem(rc_base);
        movieFragmentPresenter.doInitLinearLayoutManager();
        return null;
    }

    @Override
    protected synchronized void lazyLoad() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        movieFragmentPresenter.doConnectHttp(MyConstants.MOVIE_PRESENTER_PAGE_INDEX);
    }

    @Override
    public void onFloatingClicked() {
        rc_base.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_error:
                movieFragmentPresenter.doConnectHttp(MyConstants.MOVIE_PRESENTER_PAGE_INDEX);
                break;
        }
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
        //pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
        loadingDialog.show();
    }

    @Override
    public void onErrorVisibility(int progressVisb, int errorVisb) {
        loadingDialog.dismiss();
        Snackbar snackbar = Snackbar.make(rc_base, R.string.snakebar_text, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(Color.WHITE);
        snackbar.show();
        if (adapter.getItemCount() == 0) {
            rl_error.setVisibility(errorVisb);
        }

    }

    @Override
    public void onErrorSnakeBarAction() {
        movieFragmentPresenter.doConnectHttp(MyConstants.MOVIE_PRESENTER_PAGE_INDEX);
    }

    @Override
    public void onCompletedVisibility(int progressVisb, int errorVisb) {
        //pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
        loadingDialog.dismiss();
    }

    @Override
    public void onNotifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieFragmentPresenter.doDestroy();
    }
}
