package com.doubanapp.hbj.douban.model;

import android.content.Context;

import com.doubanapp.hbj.douban.IModel.IMusicFragmentModel;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.KuaiDiJsonData;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.utils.BoubanAPIConnectCountAlert;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public class MusicFragmentModel {

    private List<String> mData1 = new ArrayList<>();
    private List<String> mData2 = new ArrayList<>();
    private List<String> mData3 = new ArrayList<>();
    private List<String> mData4 = new ArrayList<>();
    private List<String> mData5 = new ArrayList<>();
    private IMusicFragmentModel iMusicFragmentModel;
    private Context mContext;

    public MusicFragmentModel(Context mContext, IMusicFragmentModel iMusicFragmentModel) {
        this.iMusicFragmentModel = iMusicFragmentModel;
        this.mContext = mContext;
        toConnectHttp();
    }

    private void toConnectHttp() {

        String baseUrl = MyUtils.getResourcesString(R.string.base_kuaidi_url);
        //此处加载数据
        Retrofit retrofit = MyUtils.getRetrofit(baseUrl);
        //请求网络
        retrofit.create(MyServiceInterface.class).toSearch("yuantong", "500379523313")
                //ResponseBody数据保存，和转换
                .map(new Func1<ResponseBody, KuaiDiJsonData>() {
                    @Override
                    public KuaiDiJsonData call(ResponseBody responseBody) {
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
                .subscribe(new Subscriber<KuaiDiJsonData>() {
                    @Override
                    public void onCompleted() {
                        //请求结束
                        iMusicFragmentModel.onConnectCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        iMusicFragmentModel.onConnectError();
                    }

                    @Override
                    public void onNext(KuaiDiJsonData res) {
                        //成功
                        //MyLogUtils.i(TAG, res.getData().get(0).getContext());
                        String context = res.getData().get(0).getContext();

                        for (int i = 0; i < 10; i++) {
                            mData1.add("新曲");
                        }
                        for (int i = 0; i < 10; i++) {
                            mData2.add("华语");
                        }
                        for (int i = 0; i < 10; i++) {
                            mData3.add("欧美");
                        }
                        for (int i = 0; i < 10; i++) {
                            mData4.add("日韩");
                        }
                        for (int i = 0; i < 6; i++) {
                            mData5.add("感兴趣");
                        }
                        iMusicFragmentModel.onConnectNext(mData1, mData2, mData3, mData4, mData5);
                    }

                    @Override
                    public void onStart() {
                        //开始
                        new BoubanAPIConnectCountAlert(mContext);
                        // MyLogUtils.i(TAG, "onStart");
                        iMusicFragmentModel.onConnectStart();
                    }
                });
    }


    private KuaiDiJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, KuaiDiJsonData.class);
    }
}
