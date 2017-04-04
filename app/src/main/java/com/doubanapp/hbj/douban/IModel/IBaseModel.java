package com.doubanapp.hbj.douban.IModel;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IBaseModel {
    void onConnectStart();

    void onConnectError();

    void onConnectCompleted();
}
