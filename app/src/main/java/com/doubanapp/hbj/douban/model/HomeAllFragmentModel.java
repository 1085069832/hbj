package com.doubanapp.hbj.douban.model;

import android.content.Context;

import com.doubanapp.hbj.douban.IModel.IHomeAllModel;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.HomeJsonData;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 请求book数据
 * Created by Administrator on 2017/4/7 0007.
 */
public class HomeAllFragmentModel {

    private static final String TAG = "HomeAllFragmentModel";
    private Context mContext;
    private IHomeAllModel iHomeAllModela;
    private List<String> mData = new ArrayList<>();
    private Subscription subscription;
    private int currentPage = 1;//标记第几页数据
    private int count = 20;//请求多少个数据

    public HomeAllFragmentModel(Context mContext, IHomeAllModel iHomeAllModela) {
        this.mContext = mContext;
        this.iHomeAllModela = iHomeAllModela;
    }

    public void toConnectData(String type) {
        //判断是否订阅了
        if (subscription != null && !subscription.isUnsubscribed()) {
            MyLogUtils.i(TAG, "已经在加载了");
            return;
        }
        String baseUrl = MyUtils.getResourcesString(R.string.gank_base_url);
        //此处加载数据
        Retrofit retrofit = MyUtils.getRetrofit(baseUrl);
        subscription = retrofit.create(MyServiceInterface.class).toConnectGankData("api/data/" + type + "/" + count + "/" + currentPage)
                //ResponseBody数据保存，和转换
                .map(new Func1<ResponseBody, HomeJsonData>() {
                    @Override
                    public HomeJsonData call(ResponseBody responseBody) {

                        String stringJson = null;
                        try {
                            stringJson = responseBody.string();
                            //保存json数据

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return parseJsonData(stringJson);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeJsonData>() {
                    @Override
                    public void onCompleted() {
                        //请求结束
                        iHomeAllModela.onConnectCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        iHomeAllModela.onConnectError();
                    }

                    @Override
                    public void onNext(HomeJsonData res) {
                        //成功
                        for (int i = 0; i < 30; i++) {
                            mData.add("全部");
                        }
                        iHomeAllModela.onHomeAllConnectNext(res);
                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        iHomeAllModela.onConnectStart(false, false);
                    }
                });
    }

    private HomeJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, HomeJsonData.class);
    }

    /*
* 取消rxjava订阅*/
    public void cancelHttpRequset() {
        if (subscription != null)
            subscription.unsubscribe();
    }
}