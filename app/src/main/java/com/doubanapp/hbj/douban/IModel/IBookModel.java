package com.doubanapp.hbj.douban.IModel;

import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public interface IBookModel extends IBaseModel {
    void onConnectNext(List<String> newBookData, List<View> newBookData1, List<String> newBookData2);
}
