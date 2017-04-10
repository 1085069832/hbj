package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.HomeViewPagerAdapter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.viewpagerindicator.TabPageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 主界面
 * Created by Administrator on 2017/3/17 0017.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.ti_home)
    TabPageIndicator tiHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    Unbinder unbinder;
    private HomeViewPagerAdapter adapter;

    public static HomeFragment newsInstance(int pos) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MyLogUtils.i(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fg_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new HomeViewPagerAdapter(getFragmentManager());
        vpHome.setAdapter(adapter);
        vpHome.setOffscreenPageLimit(4);
        tiHome.setViewPager(vpHome);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
