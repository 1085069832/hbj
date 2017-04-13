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

import com.doubanapp.hbj.douban.IView.IHomeAllFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.HomeNormalRcAdapter;
import com.doubanapp.hbj.douban.bean.DayHistoryJsonData;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.bean.KuaiDiJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.presenter.FragmentPresenter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.google.gson.Gson;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.BlurEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;
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
public class HomeAllFragment extends BaseFragment implements IHomeAllFragmentView {

    private static final String TAG = "HomeAllFragment";
    private boolean isFirstCreate;//是否第一次加载
    private boolean isCreateView = false;//是否创建了视图
    private FragmentPresenter homeAllFragmentPresenter;
    private MultiTypeAdapter adapter;

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

    @Override
    protected View initChildView() {
        homeAllFragmentPresenter = new FragmentPresenter(mContext, this);
        homeAllFragmentPresenter.doInitSweetSheet(rl, rc_base);
        homeAllFragmentPresenter.doRegisterMultitypeItem();
        homeAllFragmentPresenter.doInitLayoutManager();


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
        homeAllFragmentPresenter.doConnectHttp(MyConstants.HOME_ALL_PRESENTER_PAGE_INDEX);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_error:
                homeAllFragmentPresenter.doConnectHttp(MyConstants.HOME_ALL_PRESENTER_PAGE_INDEX);
                break;
            default:
        }
    }

    @Override
    public void onFloatingClicked() {
        rc_base.smoothScrollToPosition(0);
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
