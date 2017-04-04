package com.doubanapp.hbj.douban.item;

import java.util.List;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class MovieListSelectionItem implements Item {

    public List<String> content;
    public String title;

    public MovieListSelectionItem(List<String> content, String title) {
        //需要数据的类型
        this.title = title;
        this.content = content;
    }
}
