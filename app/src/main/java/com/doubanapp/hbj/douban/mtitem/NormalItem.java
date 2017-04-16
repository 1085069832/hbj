package com.doubanapp.hbj.douban.mtitem;

import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;

import java.util.List;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class NormalItem implements Item {

    public List<String> content;
    public HomeDayRecommendJsonData.ResultsBean rbContent;
    public String title;
    public int startIndex;

    public NormalItem(final List<String> content, String title, int startIndex) {
        //需要数据的类型
        this.content = content;
        this.title = title;
        this.startIndex = startIndex;
    }

    public NormalItem(final HomeDayRecommendJsonData.ResultsBean rbContent, int startIndex) {
        //需要数据的类型
        this.rbContent = rbContent;
        this.startIndex = startIndex;
    }
}
