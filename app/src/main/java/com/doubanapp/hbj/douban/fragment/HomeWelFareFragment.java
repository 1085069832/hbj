package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.WelFareRcAdapter;
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
 * 福利
 * Created by Administrator on 2017/3/31 0031.
 */
public class HomeWelFareFragment extends LazyFragment {

    private static final String TAG = "HomeWelFareFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private boolean isLoading = false;
    private ProgressBar pb_loading;
    private RecyclerView rc_home_welfare;
    private TextView iv_error;
    private List<String> mData = new ArrayList<>();
    private List<Integer> mHeight = new ArrayList<>();

    public static HomeWelFareFragment newsInstance(int pos) {
        HomeWelFareFragment fragment = new HomeWelFareFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        isFirstCreate = true;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyLogUtils.i(TAG, "onCreateView");
        //此处加载界面
        View view = inflater.inflate(R.layout.fg_home_welfare, container, false);
        rc_home_welfare = (RecyclerView) view.findViewById(R.id.rc_home_welfare);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        iv_error = (TextView) view.findViewById(R.id.tv_error);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rc_home_welfare.setLayoutManager(manager);
        for (int i = 0; i < 30; i++) {
            mData.add("c");
        }
        for (int i = 0; i < mData.size(); i++) {
            int randomHeight = (int) (Math.random() * 50);
            mHeight.add(randomHeight);
        }
        WelFareRcAdapter adapter = new WelFareRcAdapter(mData, mHeight);
        rc_home_welfare.setAdapter(adapter);





        isCreateView = true;
        lazyLoad();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initEvent();
    }

    @Override
    protected synchronized void lazyLoad() {
        MyLogUtils.i(TAG, isCreateView + "isCreateView");
        MyLogUtils.i(TAG, isVisible + "isVisible");
        if (!isFirstCreate || !isCreateView || !isVisible) {
            //不是第一次创建，或者还没有加载完view，或者没显示
            //不加载数据
            return;
        }
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
                        pb_loading.setVisibility(View.GONE);
                        iv_error.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        pb_loading.setVisibility(View.GONE);
                        iv_error.setVisibility(View.VISIBLE);
                        Toast.makeText(MyUtils.getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(KuaiDiJsonData res) {
                        //成功
                        MyLogUtils.i(TAG, res.getData().get(0).getContext());

                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        new BoubanAPIConnectCountAlert(mContext);
                        //设置第一次加载变量
                        isFirstCreate = false;
                        pb_loading.setVisibility(View.VISIBLE);
                        iv_error.setVisibility(View.GONE);
                    }
                });
    }

    private void initEvent() {

        iv_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toConnectData();
            }
        });

    }

    private KuaiDiJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, KuaiDiJsonData.class);
    }


}
