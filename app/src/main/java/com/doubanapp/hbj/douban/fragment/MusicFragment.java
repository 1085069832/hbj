package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.doubanapp.hbj.douban.IView.IMusicFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.presenter.FragmentPresenter;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 音乐
 * Created by Administrator on 2017/3/17 0017.
 */
public class MusicFragment extends BaseFragment implements IMusicFragmentView {

    private static final String TAG = "MusicFragment";
    private MultiTypeAdapter adapter;
    private FragmentPresenter musicFragmentPresenter;


    public static MusicFragment newsInstance(int pos) {
        MusicFragment fragment = new MusicFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View initChildView() {
        //Presenter
        musicFragmentPresenter = new FragmentPresenter(mContext, this);
        musicFragmentPresenter.doRegisterMultitypeItem(rc_base);
        musicFragmentPresenter.doInitLinearLayoutManager();
        return null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        musicFragmentPresenter.doConnectHttp(MyConstants.MUSIC_PRESENTER_PAGE_INDEX);
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
                musicFragmentPresenter.doConnectHttp(MyConstants.MUSIC_PRESENTER_PAGE_INDEX);
                break;
            default:
        }
    }

    /*
    * multitype*/
    @Override
    public void onRegisterMultitypeItem(MultiTypeAdapter adapter) {
        this.adapter = adapter;
        rc_base.setAdapter(adapter);
    }

    /*
    * set layoutmanager*/
    @Override
    public void onInitLayoutManager(RecyclerView.LayoutManager manager) {
        rc_base.setLayoutManager(manager);
    }

    /*
    * 开始请求*/
    @Override
    public void onStartVisibility(int progressVisb, int errorVisb) {
        //pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
        loadingDialog.show();
    }

    /*
    * 请求失败*/
    @Override
    public void onErrorVisibility(int progressVisb, int errorVisb) {
        loadingDialog.dismiss();
        if (adapter.getItemCount() == 0) {
            rl_error.setVisibility(errorVisb);
        }
    }

    @Override
    public void onErrorAppMsgClick() {
        musicFragmentPresenter.doConnectHttp(MyConstants.MUSIC_PRESENTER_PAGE_INDEX);
    }

    /*
    * 请求结束*/
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
        musicFragmentPresenter.doDestroy();
    }
}
