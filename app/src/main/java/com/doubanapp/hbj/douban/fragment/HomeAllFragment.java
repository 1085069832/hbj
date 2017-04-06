package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.HomeNormalRcAdapter;
import com.doubanapp.hbj.douban.bean.DayHistoryJsonData;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.bean.KuaiDiJsonData;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 所有
 * Created by Administrator on 2017/3/31 0031.
 */
public class HomeAllFragment extends LazyFragment {

    private static final String TAG = "HomeAllFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private boolean isLoading = false;
    private ProgressBar pb_loading;
    private RecyclerView rc_home_all;
    private RelativeLayout iv_error;
    private DrawerLayout dl_fg_home_all;
    private CheckBox cb_home_all_select_class;
    private List<String> mData = new ArrayList<>();
    private RadioGroup rg_home_select_class;
    private HomeNormalRcAdapter adapter;
    private TextView tv_home_all_title_type;
    private String gank_base_url;

    public static HomeAllFragment newsInstance(int pos) {
        HomeAllFragment fragment = new HomeAllFragment();
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
        View view = inflater.inflate(R.layout.fg_home_all, container, false);
        rc_home_all = (RecyclerView) view.findViewById(R.id.rc_home_all);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        iv_error = (RelativeLayout) view.findViewById(R.id.rl_error);
        tv_home_all_title_type = (TextView) view.findViewById(R.id.tv_home_all_title_type);
        cb_home_all_select_class = (CheckBox) view.findViewById(R.id.cb_home_all_select_class);
        dl_fg_home_all = (DrawerLayout) view.findViewById(R.id.dl_fg_home_all);
        rg_home_select_class = (RadioGroup) view.findViewById(R.id.rg_home_select_class);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc_home_all.setLayoutManager(manager);


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

        toConnectHttp();
    }

    private void toConnectHttp() {
        isFirstCreate = false;
        pb_loading.setVisibility(View.VISIBLE);
        iv_error.setVisibility(View.GONE);

        //获取有内容的日期 http://gank.io/api/day/history
        gank_base_url = MyUtils.getResourcesString(R.string.gank_base_url);
        MyUtils.sendOkHttpConnect(gank_base_url + "/api/day/history", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        pb_loading.setVisibility(View.GONE);
                        iv_error.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String dayString = response.body().string();
                DayHistoryJsonData dayHistoryJsonData = parseDayHistoryJsonData(dayString);
                //2017-03-31
                String nearestDay = dayHistoryJsonData.getResults().get(0);
                String[] split = nearestDay.split("-");
                String resultDay = "";
                for (int i = 0; i < split.length; i++) {
                    resultDay += ("/" + split[i]);// /2017/3/31
                }
                final String finalResultDay = resultDay;
                MyUtils.runOnUIThread(new Runnable() {
                    @Override
                    public void run() {
                        toConnectContentData(finalResultDay);
                    }
                });
            }
        });
    }

    private void toConnectContentData(String resultDay) {
        //此处加载数据
        Retrofit retrofit = MyUtils.getRetrofit(gank_base_url);
        //请求网络
        retrofit.create(MyServiceInterface.class).toConnecHomeDayRecommendData("api/day" + resultDay)
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
                    public void onNext(HomeDayRecommendJsonData res) {
                        //成功
                        String desc = res.getResults().getAndroid().get(0).getDesc();
                        MyLogUtils.i(TAG, desc);
                        for (int i = 0; i < 30; i++) {
                            mData.add("全部");
                        }
                        adapter = new HomeNormalRcAdapter(mData);
                        rc_home_all.setAdapter(adapter);

                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        //设置第一次加载变量
                    }
                });
    }

    private void initEvent() {

        iv_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toConnectHttp();
            }
        });

        dl_fg_home_all.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!cb_home_all_select_class.isChecked()) {
                    cb_home_all_select_class.setChecked(true);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (cb_home_all_select_class.isChecked()) {
                    cb_home_all_select_class.setChecked(false);
                }
            }
        });

        cb_home_all_select_class.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    //未选中checkbox
                    if (dl_fg_home_all.isDrawerOpen(GravityCompat.END)) {
                        dl_fg_home_all.closeDrawer(GravityCompat.END);
                    }
                } else {
                    //选中checkbox
                    if (!dl_fg_home_all.isDrawerOpen(GravityCompat.END)) {
                        dl_fg_home_all.openDrawer(GravityCompat.END);
                        //设置动画
                        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.home_all_drawer_rg_anim);
                        rg_home_select_class.startAnimation(animation);
                    }
                }
            }
        });
        rg_home_select_class.check(R.id.rb_home_all_select_all);
        rg_home_select_class.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mData.clear();
                switch (checkedId) {
                    case R.id.rb_home_all_select_all://全部
                        tv_home_all_title_type.setText("全部");
                        for (int i = 0; i < 30; i++) {
                            mData.add("全部");
                        }
                        break;
                    case R.id.rb_home_all_select_ios://iOS
                        tv_home_all_title_type.setText("iOS");
                        for (int i = 0; i < 30; i++) {
                            mData.add("iOS");
                        }
                        break;
                    case R.id.rb_home_all_select_rest://休息视频
                        tv_home_all_title_type.setText("休息视频");
                        for (int i = 0; i < 30; i++) {
                            mData.add("休息视频");
                        }
                        break;
                    case R.id.rb_home_all_select_extend://扩展资源
                        tv_home_all_title_type.setText("扩展资源");
                        for (int i = 0; i < 30; i++) {
                            mData.add("扩展资源");
                        }
                        break;
                    case R.id.rb_home_all_select_front://前端
                        tv_home_all_title_type.setText("前端");
                        for (int i = 0; i < 30; i++) {
                            mData.add("前端");
                        }
                        break;
                    case R.id.rb_home_all_select_app://app
                        tv_home_all_title_type.setText("App");
                        for (int i = 0; i < 30; i++) {
                            mData.add("App");
                        }
                        break;
                    default:
                }
                if (dl_fg_home_all.isDrawerOpen(GravityCompat.END)) {
                    dl_fg_home_all.closeDrawer(GravityCompat.END);
                }
                adapter.notifyDataSetChanged();
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

    private KuaiDiJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, KuaiDiJsonData.class);
    }
}
