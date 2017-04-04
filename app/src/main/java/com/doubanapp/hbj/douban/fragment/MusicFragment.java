package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doubanapp.hbj.douban.IView.IMusicFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.mtitem.SelectItem;
import com.doubanapp.hbj.douban.presenter.MusicFragmentPresenter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 音乐
 * Created by Administrator on 2017/3/17 0017.
 */
public class MusicFragment extends LazyFragment implements View.OnClickListener, IMusicFragmentView {

    private static final String TAG = "MusicFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private boolean isLoading = false;
    private ProgressBar pb_loading;
    private RecyclerView rc_music;
    private TextView tv_error;
    private Items items;
    private MultiTypeAdapter adapter;
    private MusicFragmentPresenter musicFragmentPresenter;


    public static MusicFragment newsInstance(int pos) {
        MusicFragment fragment = new MusicFragment();
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
        View view = inflater.inflate(R.layout.fg_music, container, false);
        rc_music = (RecyclerView) view.findViewById(R.id.rc_music);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        tv_error = (TextView) view.findViewById(R.id.tv_error);

        //Presenter
        musicFragmentPresenter = new MusicFragmentPresenter(mContext, this);
        musicFragmentPresenter.doRegisterMultitypeItem();
        musicFragmentPresenter.doInitLayoutManager();

        isCreateView = true;
        MyLogUtils.i(TAG, "isLoading" + isLoading);
        lazyLoad();
        return view;
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

        //设置第一次加载变量
        isFirstCreate = false;
        //获取数据
        musicFragmentPresenter.doConnectHttp();
    }


    @Override
    public void onRegisterMultitypeItem(Items items, MultiTypeAdapter adapter) {
        this.items = items;
        this.adapter = adapter;
    }

    /*
        * set layoutmanager*/
    @Override
    public void onInitLayoutManager(RecyclerView.LayoutManager manager) {
        rc_music.setLayoutManager(manager);
    }

    /*
    * 连接网络获取数据*/
    @Override
    public void onConnectHttp(List<String> newMusicData, List<String> mandoPopMusicData, List<String> westernMusicData, List<String> jSKMusicData, List<String> mayYouLikeMusicData) {
        items.add(new NormalItem(newMusicData, "新曲", MyConstants.MUSIC_NEW_MUSIC_INDEX));
        items.add(new NormalItem(mandoPopMusicData, "华语歌曲", MyConstants.MUSIC_HUAYU_MUSIC_INDEX));
        items.add(new NormalItem(westernMusicData, "欧美歌曲", MyConstants.MUSIC_OUMEI_MUSIC_INDEX));
        items.add(new NormalItem(jSKMusicData, "日韩歌曲", MyConstants.MUSIC_RIHAN_MUSIC_INDEX));
        items.add(new MayYouLikeItem(mayYouLikeMusicData, "你可能感兴趣", MyConstants.MUSIC_MAY_YOU_LIKE_MUSIC_INDEX));
        items.add(new SelectItem("选音乐"));
        rc_music.setAdapter(adapter);
    }

    /*
    * 开始请求*/
    @Override
    public void onStartVisibility() {
        pb_loading.setVisibility(View.VISIBLE);
        tv_error.setVisibility(View.GONE);
    }

    /*
    * 请求失败*/
    @Override
    public void onErrorVisibility() {
        pb_loading.setVisibility(View.GONE);
        tv_error.setVisibility(View.VISIBLE);
        Toast.makeText(MyUtils.getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
    }

    /*
    * 请求结束*/
    @Override
    public void onCompletedVisibility() {
        pb_loading.setVisibility(View.GONE);
        tv_error.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_error:

                break;
            default:
        }
    }
}
