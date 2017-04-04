package com.doubanapp.hbj.douban.IView;

import android.support.v7.widget.RecyclerView;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IFragmentBaseView {

    void onRegisterMultitypeItem(MultiTypeAdapter adapter);

    void onInitLayoutManager(RecyclerView.LayoutManager manager);

    void onStartVisibility();

    void onErrorVisibility();

    void onCompletedVisibility();

    void onSetAdapter();
}
