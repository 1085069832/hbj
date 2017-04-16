package com.doubanapp.hbj.douban.model;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubanapp.hbj.douban.IModel.IHomeDayRecommendModel;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.DayHistoryJsonData;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
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
    private String gank_base_url;
    private Subscription contentSubscription;
    private Subscription daySubscription;
    private Retrofit retrofit;

    public HomeDayRecommendFragmentModel(Context mContext, IHomeDayRecommendModel iHomeDayRecommendModel) {
        this.mContext = mContext;
        this.iHomeDayRecommendModel = iHomeDayRecommendModel;
    }

    public void toConnectHttp() {
        gank_base_url = MyUtils.getResourcesString(R.string.gank_base_url);
        //获取有内容的日期 http://gank.io/api/day/history
        retrofit = MyUtils.getRetrofit(gank_base_url);
        daySubscription = retrofit.create(MyServiceInterface.class).toConnecHomeDayRecommendData(gank_base_url + "/api/day/history")
                //ResponseBody数据保存，和转换
                .map(new Func1<ResponseBody, DayHistoryJsonData>() {
                    @Override
                    public DayHistoryJsonData call(ResponseBody responseBody) {

                        String stringJson = null;
                        try {
                            stringJson = responseBody.string();
                            //保存json数据

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return parseDayHistoryJsonData(stringJson);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DayHistoryJsonData>() {
                    @Override
                    public void onCompleted() {
                        //请求结束
                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        iHomeDayRecommendModel.onConnectError();
                    }

                    @Override
                    public void onNext(DayHistoryJsonData dayHistoryJsonData) {
                        //2017-03-31
                        String nearestDay = dayHistoryJsonData.getResults().get(0);
                        String[] split = nearestDay.split("-");
                        String resultDay = "";
                        for (int i = 0; i < split.length; i++) {
                            resultDay += ("/" + split[i]);// /2017/3/31
                        }
                        //获取有内容日期的数据
                        toConnectContentData(resultDay);
                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        iHomeDayRecommendModel.onConnectStart();
                    }
                });


    }

    private void toConnectContentData(String resultDay) {
        //此处加载数据
        contentSubscription = retrofit.create(MyServiceInterface.class).toConnecHomeDayRecommendData("api/day" + resultDay)
                //ResponseBody数据保存，和转换
                .map(new Func1<ResponseBody, HomeDayRecommendJsonData>() {
                    @Override
                    public HomeDayRecommendJsonData call(ResponseBody responseBody) {

                        String stringJson = null;
                        try {
                            stringJson = responseBody.string();
                            //保存json数据

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return parseContentJsonData(stringJson);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeDayRecommendJsonData>() {
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
                    public void onNext(HomeDayRecommendJsonData res) {
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
                    }
                });
    }

    private HomeDayRecommendJsonData parseContentJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, HomeDayRecommendJsonData.class);
    }

    private DayHistoryJsonData parseDayHistoryJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, DayHistoryJsonData.class);
    }

    /*
    * 取消rxjava订阅*/
    public void cancelHttpRequset() {
        if (contentSubscription != null)
            contentSubscription.unsubscribe();

        if (daySubscription != null)
            daySubscription.unsubscribe();
    }
}