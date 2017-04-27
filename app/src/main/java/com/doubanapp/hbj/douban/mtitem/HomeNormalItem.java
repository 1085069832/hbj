package com.doubanapp.hbj.douban.mtitem;

import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeNormalItem implements Item {

    public String title;
    public int position;
    public int pageIndex;
    public HomeDayRecommendJsonData homeDayRecommendJsonDataRes;
    public String type;
    public String des;
    public String who;
    public String time;
    public String image;

    public HomeNormalItem(String title, int position) {
        this.title = title;
        this.position = position;
    }

    public HomeNormalItem(HomeDayRecommendJsonData homeDayRecommendJsonDataRes, String title, int pageIndex) {
        this.homeDayRecommendJsonDataRes = homeDayRecommendJsonDataRes;
        this.title = title;
        this.pageIndex = pageIndex;
    }

    public HomeNormalItem(String type, String des, String who, String time, String image, int position, int pageIndex) {
        this.position = position;
        this.pageIndex = pageIndex;
        this.type = type;
        this.des = des;
        this.who = who;
        this.time = time;
        this.image = image;
    }
}
