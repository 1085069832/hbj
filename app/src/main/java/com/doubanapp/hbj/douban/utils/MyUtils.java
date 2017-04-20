package com.doubanapp.hbj.douban.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.global.MyApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class MyUtils {
    //获取context ************************************************************
    public static Context getContext() {
        return MyApplication.getContext();
    }
    //************************************************************

    //获取handler ************************************************************
    public static Handler getHandler() {
        return MyApplication.getHandler();
    }
    //************************************************************

    //更新UI ************************************************************
    //是否是主线程
    public static boolean isMainThreadId() {
        int myTid = android.os.Process.myTid();
        if (myTid == MyApplication.getTid())
            return true;
        return false;
    }

    public static void runOnUIThread(Runnable r) {
        if (isMainThreadId()) {
            // 已经是主线程, 直接运行
            r.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getHandler().post(r);
        }
    }
    //************************************************************

    //获取资源 ************************************************************
    //获取string资源
    public static String getResourcesString(int id) {
        return getContext().getResources().getString(id);
    }

    //获取string-array资源
    public static String[] getResourcesArrayString(int id) {
        return getContext().getResources().getStringArray(id);
    }
    //************************************************************

    //SPF *************************************************************
    public static void putStringSPF(String key, String value) {
        getContext().getSharedPreferences(MyConstants.MYSPF, Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public static String getStringSPF(String key, String defaultvalue) {
        return getContext().getSharedPreferences(MyConstants.MYSPF, Context.MODE_PRIVATE).getString(key, defaultvalue);
    }

    public static void putIntSPF(String key, int value) {
        getContext().getSharedPreferences(MyConstants.MYSPF, Context.MODE_PRIVATE).edit().putInt(key, value).apply();
    }

    public static int getIntSPF(String key, int defaultvalue) {
        return getContext().getSharedPreferences(MyConstants.MYSPF, Context.MODE_PRIVATE).getInt(key, defaultvalue);
    }

    public static void putBooleanSPF(String key, boolean value) {
        getContext().getSharedPreferences(MyConstants.MYSPF, Context.MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    public static boolean getBooleanSPF(String key, boolean defaultvalue) {
        return getContext().getSharedPreferences(MyConstants.MYSPF, Context.MODE_PRIVATE).getBoolean(key, defaultvalue);
    }
    //*************************************************************

    //连接服务器 *************************************************************

    public static Retrofit getRetrofit(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000L, TimeUnit.MILLISECONDS)
                .cache(new Cache(getContext().getCacheDir(), 10 * 1024 * 1024))
                .build();
        //设置Retrofit，并把okhttp设置进来
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }


    public static void sendOkHttpConnect(String url, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //*************************************************************

    //dp和px转换 *************************************************************

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    //*************************************************************

    //获取缓存目录*************************************************************
    public static File getDiskCacheDir() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
                || !Environment.isExternalStorageRemovable()) {
            return getContext().getExternalCacheDir();
        } else {
            return getContext().getCacheDir();
        }
    }
    //*************************************************************
}
