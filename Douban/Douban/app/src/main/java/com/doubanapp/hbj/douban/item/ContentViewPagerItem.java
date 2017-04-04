package com.doubanapp.hbj.douban.item;

import android.view.View;

import java.util.List;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class ContentViewPagerItem implements Item {


    public List<View> mViews;
    public int startIndex;
    public String title;

    public ContentViewPagerItem(List<View> mViews, String title, int startIndex) {
        //需要数据的类型
        this.mViews = mViews;
        this.title = title;
        this.startIndex = startIndex;
    }
}
