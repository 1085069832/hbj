package com.doubanapp.hbj.douban.IModel;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IMovieModel extends IBaseModel {
    void onMovieConnectNext(List<String> movieHotShowData, List<String> movieComingSoonData, List<String> movieListSelectionData, List<String> movieMayYouLikeData, List<View> movieSelectData);
}
