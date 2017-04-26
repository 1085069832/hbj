package com.doubanapp.hbj.douban.model;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.doubanapp.hbj.douban.IModel.IBookModel;
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
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 请求book数据
 * Created by Administrator on 2017/4/7 0007.
 */
public class BookFragmentModel {

    private static final String TAG = "BookFragmentModel";
    private Context mContext;
    private IBookModel iBookModel;
    private List<String> mData1 = new ArrayList<>();
    private List<String> mData3 = new ArrayList<>();
    private List<View> mData2 = new ArrayList<>();
    private Subscription subscription;

    public BookFragmentModel(Context mContext, IBookModel iBookModel) {
        this.mContext = mContext;
        this.iBookModel = iBookModel;
    }

    public void toConnectData() {
        //判断是否订阅了
        if (subscription != null && !subscription.isUnsubscribed()) {
            MyLogUtils.i(TAG, "已经在加载了");
            return;
        }
        String baseUrl = MyUtils.getResourcesString(R.string.base_kuaidi_url);
        //此处加载数据
        Retrofit retrofit = MyUtils.getRetrofit(baseUrl);
        subscription = retrofit.create(MyServiceInterface.class).toSearch("yuantong", "500379523313")
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
                        iBookModel.onConnectCompleted();

                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        iBookModel.onConnectError();
                    }

                    @Override
                    public void onNext(KuaiDiJsonData res) {
                        //成功
                        MyLogUtils.i(TAG, res.getData().get(0).getContext());
                        for (int i = 0; i < 10; i++) {
                            mData1.add("新书");
                        }
                        for (int i = 0; i < 4; i++) {
                            TextView textView = new TextView(MyUtils.getContext());
                            textView.setText("四大名著" + i);
                            textView.setTextSize(35);
                            textView.setTextColor(Color.BLUE);
                            mData2.add(textView);
                        }
                        for (int i = 0; i < 6; i++) {
                            mData3.add("感兴趣");
                        }
                        iBookModel.onBookConnectNext(mData1, mData2, mData3);

                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        new BoubanAPIConnectCountAlert(mContext);
                        iBookModel.onConnectStart(false,false);
                    }
                });
    }

    private KuaiDiJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, KuaiDiJsonData.class);
    }

    /*
    * 取消rxjava订阅*/
    public void cancelHttpRequset() {
        if (subscription != null)
            subscription.unsubscribe();
    }
}
