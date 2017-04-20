package com.doubanapp.hbj.douban.mtitem;

import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeNormalItem implements Item {

    public String title;
    public int position;
    public HomeDayRecommendJsonData res;

    public HomeNormalItem(String title, int position) {
        this.title = title;
        this.position = position;
    }

    public HomeNormalItem(HomeDayRecommendJsonData res, String title, int position) {
        this.title = title;
        this.position = position;
        this.res = res;
    }
}
