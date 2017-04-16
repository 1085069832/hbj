package com.doubanapp.hbj.douban.mtitem;

import java.util.List;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeWelFareItem implements Item {

    public List<String> mData;
    public int addHeight;

    public HomeWelFareItem(List<String> mData,int addHeight) {
        this.mData = mData;
        this.addHeight = addHeight;
    }
}
