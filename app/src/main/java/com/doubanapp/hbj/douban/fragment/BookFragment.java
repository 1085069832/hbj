package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doubanapp.hbj.douban.IView.IBookFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.presenter.MyFragmentPresenter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 书籍
 * Created by Administrator on 2017/3/17 0017.
 */
public class BookFragment extends BaseFragment implements IBookFragmentView {

    private static final String TAG = "BookFragment";
    private MultiTypeAdapter adapter;
    private MyFragmentPresenter bookFragmentPresenter;

    public static BookFragment newsInstance(int pos) {
        BookFragment fragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initChildView() {
        MyLogUtils.i(TAG, "onCreateView");
        bookFragmentPresenter = new MyFragmentPresenter(mContext, this);
        bookFragmentPresenter.doRegisterMultitypeItem(MyConstants.BOOK_SELECT_PAGE_INDEX);
        bookFragmentPresenter.doInitLayoutManager();
        return null;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bookFragmentPresenter.doConnectHttp(MyConstants.BOOK_SELECT_PAGE_INDEX);
    }

    @Override
    protected synchronized void lazyLoad() {
    }


    @Override
    public void onFloatingClicked() {
        rc_base.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_error:
                bookFragmentPresenter.doConnectHttp(MyConstants.BOOK_SELECT_PAGE_INDEX);
                break;
            default:
        }
    }

    @Override
    public void onRegisterMultitypeItem(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onInitLayoutManager(RecyclerView.LayoutManager manager) {
        rc_base.setLayoutManager(manager);
    }

    @Override
    public void onStartVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onErrorVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onCompletedVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onSetAdapter() {
        rc_base.setAdapter(adapter);
    }
}
