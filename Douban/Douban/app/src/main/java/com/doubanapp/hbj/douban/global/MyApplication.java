package com.doubanapp.hbj.douban.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2017/3/14.
 */
public class MyApplication extends Application {

    public static Context mContext;
    private static Handler mHandler;
    private static int myTid;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

        mHandler = new Handler();

        myTid = android.os.Process.myTid();

    }

    public static Context getContext() {

        return mContext;
    }

    public static Handler getHandler() {

        return mHandler;
    }

    public static int getTid() {

        return myTid;
    }
}
