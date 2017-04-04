package com.doubanapp.hbj.douban.item;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class SelectItem implements Item {

    public String title;

    public SelectItem(String title) {
        //需要数据的类型
        this.title = title;
    }
}
