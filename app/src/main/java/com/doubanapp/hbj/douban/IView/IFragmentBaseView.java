package com.doubanapp.hbj.douban.IView;

import android.support.v7.widget.RecyclerView;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * IFragmentbaseView
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IFragmentBaseView {

    void onRegisterMultitypeItem(MultiTypeAdapter adapter);

    void onInitLayoutManager(RecyclerView.LayoutManager manager);

    void onStartVisibility(int progressVisb, int errorVisb);

    void onErrorVisibility(int progressVisb, int errorVisb);

    void onRefreshCompleted();

    void onCompletedVisibility(int progressVisb, int errorVisb);

    void onNotifyDataSetChanged();

}
