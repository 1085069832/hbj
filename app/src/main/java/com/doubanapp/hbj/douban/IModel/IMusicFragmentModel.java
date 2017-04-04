package com.doubanapp.hbj.douban.IModel;

import java.util.List;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IMusicFragmentModel extends IBaseModel {


    void onConnectNext(List<String> newMusicData, List<String> mandoPopMusicData, List<String> westernMusicData, List<String> jSKMusicData,
                       List<String> mayYouLikeMusicData);
}
