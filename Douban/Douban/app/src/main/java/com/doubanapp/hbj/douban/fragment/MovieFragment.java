package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.KuaiDiJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.item.ContentIconItem;
import com.doubanapp.hbj.douban.item.MayYouLikeItem;
import com.doubanapp.hbj.douban.item.MovieListSelectionItem;
import com.doubanapp.hbj.douban.item.NormalItem;
import com.doubanapp.hbj.douban.item.SelectItem;
import com.doubanapp.hbj.douban.mtprovider.ContentIconProvider;
import com.doubanapp.hbj.douban.mtprovider.MayYouLikeProvider;
import com.doubanapp.hbj.douban.mtprovider.MovieListSelectionProvider;
import com.doubanapp.hbj.douban.mtprovider.NormalProvider;
import com.doubanapp.hbj.douban.mtprovider.SelectProvider;
import com.doubanapp.hbj.douban.utils.BoubanAPIConnectCountAlert;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 电影
 * Created by Administrator on 2017/3/17 0017.
 */
public class MovieFragment extends LazyFragment {

    private static final String TAG = "MovieFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private boolean isLoading = false;
    private ProgressBar pb_loading;
    private RecyclerView rc_movie;
    private TextView iv_error;
    private Items items;
    private List<String> mData1 = new ArrayList<>();
    private List<String> mData2 = new ArrayList<>();
    private List<String> mData3 = new ArrayList<>();
    private List<String> mData4 = new ArrayList<>();
    private List<View> mData5 = new ArrayList<>();
    private LinearLayoutManager manager;

    public static MovieFragment newsInstance(int pos) {
        MovieFragment fragment = new MovieFragment();
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
        View view = inflater.inflate(R.layout.fg_movie, container, false);
        rc_movie = (RecyclerView) view.findViewById(R.id.rc_movie);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        iv_error = (TextView) view.findViewById(R.id.tv_error);
        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc_movie.setLayoutManager(manager);
        //Item是一个布局集，可以添加很多布局
        items = new Items();
        //new一个MultiTypeAdapter
        MultiTypeAdapter adapter = new MultiTypeAdapter(items);
        //注册MultiTypeAdapter，绑定 Image.class和 ImageViewProvider，此处可以在application中使用GlobalMultiTypePool.register(Image.class, new ImageViewProvider())注册，然后在Activity中使用adapter.applyGlobalMultiTypePool()调用，方便整理代码
        adapter.register(NormalItem.class, new NormalProvider(mContext));
        adapter.register(ContentIconItem.class, new ContentIconProvider(mContext));
        adapter.register(MovieListSelectionItem.class, new MovieListSelectionProvider(mContext));
        adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MOVIE_SELECT_MOVIE_INDEX));
        //给Image添加数据，并添加到item中
        for (int i = 0; i < 10; i++) {
            mData1.add("正在热映");
        }
        for (int i = 0; i < 10; i++) {
            mData2.add("即将上映");
        }
        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(MyUtils.getContext());
            textView.setText("强力推荐" + i);
            textView.setTextSize(35);
            textView.setTextColor(Color.BLUE);
            mData5.add(textView);
        }
        mData3.add("Top250");
        mData3.add("新片");
        mData3.add("欧美");
        mData3.add("日韩");
        for (int i = 0; i < 6; i++) {
            mData4.add("感兴趣");
        }
        items.add(new NormalItem(mData1, "正在热映", MyConstants.MOVIE_HOT_SHOW_INDEX));
        items.add(new ContentIconItem(mData5, "热点", MyConstants.MOVIE_CONTENT_ICON_INDEX));
        items.add(new NormalItem(mData2, "即将上映", MyConstants.MOVIE_COMING_SOON_INDEX));
        items.add(new MovieListSelectionItem(mData3, "榜单精选"));
        items.add(new MayYouLikeItem(mData4, "你可能感兴趣", MyConstants.MOVIE_MAY_YOU_LIKE_INDEX));
        items.add(new SelectItem("选电影"));
        rc_movie.setAdapter(adapter);


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
