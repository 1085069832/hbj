package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.doubanapp.hbj.douban.IView.IDayRecommendFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.KuaiDiJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.mtitem.ButtomItem;
import com.doubanapp.hbj.douban.mtitem.ContentIconItem;
import com.doubanapp.hbj.douban.mtitem.ContentTitleViewPagerItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.presenter.FragmentPresenter;
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
 * 每日推荐
 * Created by Administrator on 2017/3/31 0031.
 */
public class HomeDayRecommendFragment extends BaseFragment implements IDayRecommendFragmentView {

    private static final String TAG = "HomeDayRecommendFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private MultiTypeAdapter adapter;
    private FragmentPresenter homeDayReFragmentPresenter;

    public static HomeDayRecommendFragment newsInstance(int pos) {
        HomeDayRecommendFragment fragment = new HomeDayRecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstCreate = true;
        MyLogUtils.i(TAG, "onCreate");
    }

    @Override
    protected View initChildView() {
        MyLogUtils.i(TAG, "initChildView");
        homeDayReFragmentPresenter = new FragmentPresenter(mContext, this);
        homeDayReFragmentPresenter.doRegisterMultitypeItem();
        homeDayReFragmentPresenter.doInitLayoutManager();

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
        homeDayReFragmentPresenter.doConnectHttp(MyConstants.HOME_DAYRECOMMEND_PRESENTER_PAGE_INDEX);
    }

    @Override
    public void onFloatingClicked() {
        rc_base.smoothScrollToPosition(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_error:
                homeDayReFragmentPresenter.doConnectHttp(MyConstants.HOME_DAYRECOMMEND_PRESENTER_PAGE_INDEX);
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
        isFirstCreate = false;
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
    public void onSetMTAdapter() {
        rc_base.setAdapter(adapter);
    }
}
