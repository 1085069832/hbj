package com.doubanapp.hbj.douban.IModel;

import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public interface IHomeDayRecommendModel extends IBaseModel {
    void onHomeDayRecommendConnectNext(HomeDayRecommendJsonData res);
}
