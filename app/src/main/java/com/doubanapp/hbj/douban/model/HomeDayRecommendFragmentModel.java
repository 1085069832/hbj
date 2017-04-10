package com.doubanapp.hbj.douban.model;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doubanapp.hbj.douban.IModel.IHomeDayRecommendModel;
import com.doubanapp.hbj.douban.IModel.IMovieModel;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.KuaiDiJsonData;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.utils.BoubanAPIConnectCountAlert;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
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
 * 请求book数据
 * Created by Administrator on 2017/4/7 0007.
 */
public class HomeDayRecommendFragmentModel {

    private static final String TAG = "MovieFragmentModel";
    private Context mContext;
    private IHomeDayRecommendModel iHomeDayRecommendModel;
    private List<String> mData1 = new ArrayList<>();
    private List<String> mData2 = new ArrayList<>();
    private List<View> mData3 = new ArrayList<>();
    private List<View> mData4 = new ArrayList<>();
    private List<View> mData5 = new ArrayList<>();
    private List<String> mData6 = new ArrayList<>();
    private List<String> mData7 = new ArrayList<>();
    private List<View> mData8 = new ArrayList<>();

    public HomeDayRecommendFragmentModel(Context mContext, IHomeDayRecommendModel iHomeDayRecommendModel) {
        this.mContext = mContext;
        this.iHomeDayRecommendModel = iHomeDayRecommendModel;
        toConnectData();
    }

    private void toConnectData() {
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
                        iHomeDayRecommendModel.onConnectCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        iHomeDayRecommendModel.onConnectError();
                    }

                    @Override
                    public void onNext(KuaiDiJsonData res) {
                        //成功
                        MyLogUtils.i(TAG, res.getData().get(0).getContext());
                        for (int i = 0; i < 4; i++) {
                            ImageView imageView = new ImageView(MyUtils.getContext());
                            Glide.with(mContext).load(R.mipmap.navigation_title_icon).crossFade().centerCrop().into(imageView);
                            mData4.add(imageView);
                        }
                        for (int i = 0; i < 3; i++) {
                            mData1.add("Android");
                        }
                        for (int i = 0; i < 3; i++) {
                            mData2.add("前端");
                        }
                        for (int i = 0; i < 3; i++) {
                            mData6.add("iOS");
                        }
                        for (int i = 0; i < 3; i++) {
                            mData7.add("App");
                        }
                        for (int i = 0; i < 1; i++) {
                            TextView textView = new TextView(MyUtils.getContext());
                            textView.setText("休息视频" + i);
                            textView.setTextSize(35);
                            textView.setTextColor(Color.BLUE);
                            mData3.add(textView);
                        }
                        for (int i = 0; i < 1; i++) {
                            TextView textView = new TextView(MyUtils.getContext());
                            textView.setText("更多推荐" + i);
                            textView.setTextSize(35);
                            textView.setTextColor(Color.BLUE);
                            mData8.add(textView);
                        }
                        for (int i = 0; i < 1; i++) {
                            TextView textView = new TextView(MyUtils.getContext());
                            textView.setText("福利" + i);
                            textView.setTextSize(35);
                            textView.setTextColor(Color.BLUE);
                            mData5.add(textView);
                        }
                        iHomeDayRecommendModel.onHomeDayRecommendConnectNext(mData4, mData1, mData2, mData6, mData7,
                                mData3, mData8, mData5);
                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        new BoubanAPIConnectCountAlert(mContext);
                        iHomeDayRecommendModel.onConnectStart();
                    }
                });

    }

    private KuaiDiJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, KuaiDiJsonData.class);
    }
}