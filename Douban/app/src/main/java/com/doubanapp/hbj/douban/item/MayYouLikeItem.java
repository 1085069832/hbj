package com.doubanapp.hbj.douban.item;

import java.util.List;

import me.drakeet.multitype.Item;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class MayYouLikeItem implements Item {

    public List<String> content;
    public String title;
    public int startIndex;

    public MayYouLikeItem(final List<String> content, String title, int startIndex) {
        //需要数据的类型
        this.content = content;
        this.title = title;
        this.startIndex = startIndex;
    }
}
