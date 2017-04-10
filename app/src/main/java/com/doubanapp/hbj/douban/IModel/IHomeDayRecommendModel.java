package com.doubanapp.hbj.douban.IModel;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public interface IHomeDayRecommendModel extends IBaseModel {
    void onHomeDayRecommendConnectNext(List<View> vpTitleData, List<String> androidData, List<String> frontData,
                                       List<String> iosData, List<String> appData, List<View> restData,
                                       List<View> moreRecommendData, List<View> welFareData);
}
