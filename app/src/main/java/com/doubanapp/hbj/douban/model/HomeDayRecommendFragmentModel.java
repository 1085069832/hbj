package com.doubanapp.hbj.douban.model;

import android.content.Context;

import com.doubanapp.hbj.douban.IModel.IHomeDayRecommendModel;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.DayHistoryJsonData;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.google.gson.Gson;

import java.io.IOException;
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

    private static final String TAG = "HomeDayRecommendFragmentModel";
    private Context mContext;
    private IHomeDayRecommendModel iHomeDayRecommendModel;
    private String gank_base_url;
    private Subscription contentSubscription;
    private Subscription daySubscription;
    private Retrofit retrofit;
    private int pagePosition = 0;//记录加载更多索引
    private int currentDayCount = 0;//标记第一次加载的索引
    private boolean isFirstLoad = true;//记录是否第一次加载

    public HomeDayRecommendFragmentModel(Context mContext, IHomeDayRecommendModel iHomeDayRecommendModel) {
        this.mContext = mContext;
        this.iHomeDayRecommendModel = iHomeDayRecommendModel;
    }

    public void toConnectHttp(final boolean isLoadMore) {
        //判断是否订阅了
        if (daySubscription != null && !daySubscription.isUnsubscribed() || contentSubscription != null
                && !contentSubscription.isUnsubscribed()) {
            MyLogUtils.i(TAG, "已经在加载了");
            return;
        }
        MyLogUtils.i(TAG, "toConnectHttp");
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
                        if (isLoadMore)
                            pagePosition--;
                        //错误回调
                        iHomeDayRecommendModel.onConnectError();
                    }

                    @Override
                    public void onNext(DayHistoryJsonData dayHistoryJsonData) {
                        //2017-03-31
                        String nearestDay = dayHistoryJsonData.getResults().get(pagePosition).replace("-", "/");
                        List<String> dayResults = dayHistoryJsonData.getResults();
                        //获取有内容日期的数据
                        if (isLoadMore) {
                            //加载更多
                            toConnectDayRecommendData(nearestDay, false);
                        } else {
                            //不是加载更多
                            if (dayResults.size() == currentDayCount) {
                                //下拉刷新没有新数据
                                MyLogUtils.i(TAG, dayResults.size() - currentDayCount + " 下拉刷新没有新数据");
                                iHomeDayRecommendModel.onRefreshResult(0);
                            } else {
                                if (pagePosition == 0 && isFirstLoad) {
                                    isFirstLoad = false;
                                    MyLogUtils.i(TAG, dayResults.get(0) + " 第一次加载");
                                    //第一次加载
                                    toConnectDayRecommendData(dayResults.get(0).replace("-", "/"), false);
                                } else {
                                    MyLogUtils.i(TAG, dayResults.size() - currentDayCount + " 下拉刷新有新数据");
                                    //下拉刷新有新数据
                                    toConnectDayRecommendData(dayResults.get(dayResults.size() - currentDayCount - 1).replace("-", "/"), true);
                                }
                            }
                        }
                        //记录第一次加载数据大小
                        if (pagePosition == 0)
                            currentDayCount = dayResults.size();
                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        if (isLoadMore) {
                            //加载更多
                            iHomeDayRecommendModel.onConnectStart(true);
                            pagePosition++;
                        } else {
                            if (pagePosition == 0 && isFirstLoad) {
                                //第一次加载
                                iHomeDayRecommendModel.onConnectStart(false);
                            } else {
                                //下拉刷新
                                iHomeDayRecommendModel.onConnectStart(true);
                            }
                        }
                    }
                });
    }

    private void toConnectDayRecommendData(final String day, final boolean isRefresh) {
        //此处加载数据
        contentSubscription = retrofit.create(MyServiceInterface.class).toConnecHomeDayRecommendData("api/day/" + day)
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
                        iHomeDayRecommendModel.onHomeDayRecommendConnectNext(res, day, isRefresh);
                        //通知刷新了多少数据
                        if (isRefresh) {
                            iHomeDayRecommendModel.onRefreshResult(res.getCategory().size());
                        }
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