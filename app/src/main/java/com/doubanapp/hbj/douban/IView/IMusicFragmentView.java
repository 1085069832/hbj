package com.doubanapp.hbj.douban.IView;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public interface IMusicFragmentView {

    void onRegisterMultitypeItem(Items items, MultiTypeAdapter adapter);

    void onInitLayoutManager(RecyclerView.LayoutManager manager);

    void onConnectHttp(List<String> newMusicData, List<String> mandoPopMusicData, List<String> westernMusicData, List<String> jSKMusicData,
                       List<String> mayYouLikeMusicData);

    void onStartVisibility();

    void onErrorVisibility();

    void onCompletedVisibility();
}
