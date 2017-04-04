package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
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
public class MusicFragment extends BaseFragment implements View.OnClickListener, IMusicFragmentView {

    private static final String TAG = "MusicFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private boolean isLoading = false;
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

    @Override
    protected View initChildView() {

        tv_error.setOnClickListener(this);
        //Presenter
        musicFragmentPresenter = new MusicFragmentPresenter(mContext, this);
        musicFragmentPresenter.doRegisterMultitypeItem();
        musicFragmentPresenter.doInitLayoutManager();


        isCreateView = true;
        MyLogUtils.i(TAG, "isLoading" + isLoading);
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

        //设置第一次加载变量
        isFirstCreate = false;
        //获取数据
        musicFragmentPresenter.doConnectHttp();
    }


    /*
    * multitype*/
    @Override
    public void onRegisterMultitypeItem(MultiTypeAdapter adapter) {
        this.adapter = adapter;
    }

    /*
    * set layoutmanager*/
    @Override
    public void onInitLayoutManager(RecyclerView.LayoutManager manager) {
        rc_base.setLayoutManager(manager);
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
    public void onSetAdapter() {
        rc_base.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_error:
                musicFragmentPresenter.doConnectHttp();
                break;
            default:
        }
    }
}
