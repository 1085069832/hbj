package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doubanapp.hbj.douban.IView.IDayRecommendFragmentView;
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

import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 每日推荐
 * Created by Administrator on 2017/3/31 0031.
 */
public class HomeDayRecommendFragment extends BaseFragment implements IDayRecommendFragmentView {

    private static final String TAG = "HomeDayRecommendFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private boolean isLoading = false;
    private List<String> mData1 = new ArrayList<>();
    private List<String> mData2 = new ArrayList<>();
    private List<View> mData3 = new ArrayList<>();
    private List<View> mData4 = new ArrayList<>();
    private List<View> mData5 = new ArrayList<>();
    private List<String> mData6 = new ArrayList<>();
    private List<String> mData7 = new ArrayList<>();
    private List<View> mData8 = new ArrayList<>();
    private MultiTypeAdapter adapter;

    public static HomeDayRecommendFragment newsInstance(int pos) {
        HomeDayRecommendFragment fragment = new HomeDayRecommendFragment();
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

    @Override
    protected View initChildView() {

        isCreateView = true;
        lazyLoad();
        return null;
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
                        //iv_error.setVisibility(View.GONE);

                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        pb_loading.setVisibility(View.GONE);
                        //iv_error.setVisibility(View.VISIBLE);
                        Toast.makeText(MyUtils.getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
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
                        /*items.add(new ContentTitleViewPagerItem(mData4, MyConstants.HOME_CONTENT_TITLE_VP_INDEX));
                        items.add(new NormalItem(mData1, "Android", MyConstants.HOME_ANDROID_INDEX));
                        items.add(new NormalItem(mData6, "iOS", MyConstants.HOME_IOS_INDEX));
                        items.add(new ContentIconItem(mData8, "更多推荐", MyConstants.HOME_CONTENT_MORE_RECOMMEND_ICON_INDEX));
                        items.add(new NormalItem(mData2, "前端", MyConstants.HOME_FRONT_INDEX));
                        items.add(new NormalItem(mData7, "App", MyConstants.HOME_APP_INDEX));
                        items.add(new ContentIconItem(mData3, "休息视频", MyConstants.HOME_CONTENT_REST_ICON_INDEX));
                        items.add(new ContentIconItem(mData5, "福利", MyConstants.HOME_CONTENT_WELFARE_ICON_INDEX));
                        items.add(new ButtomItem());

                        rc_home_day_recommend.setAdapter(adapter);*/

                    }

                    @Override
                    public void onStart() {
                        //开始
                        MyLogUtils.i(TAG, "onStart");
                        new BoubanAPIConnectCountAlert(mContext);
                        //设置第一次加载变量
                        isFirstCreate = false;
                        pb_loading.setVisibility(View.VISIBLE);
                        //iv_error.setVisibility(View.GONE);
                    }
                });
    }

    private KuaiDiJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, KuaiDiJsonData.class);
    }

    @Override
    public void onFloatingClicked() {
        rc_base.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_error:

                break;
        }
    }

    @Override
    public void onRegisterMultitypeItem(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onInitLayoutManager(RecyclerView.LayoutManager manager) {
        rc_base.setLayoutManager(manager);
    }

    @Override
    public void onStartVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onErrorVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onCompletedVisibility(int progressVisb, int errorVisb) {
        pb_loading.setVisibility(progressVisb);
        rl_error.setVisibility(errorVisb);
    }

    @Override
    public void onSetAdapter() {
        rc_base.setAdapter(adapter);
    }
}
