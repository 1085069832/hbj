package com.doubanapp.hbj.douban.IModel;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IMusicModel extends IBaseModel {


    void onMusicConnectNext(List<String> newMusicData, List<View> musicHotData, List<String> mandoPopMusicData, List<String> westernMusicData, List<String> jSKMusicData,
                            List<String> mayYouLikeMusicData);
}
