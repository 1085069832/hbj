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

/**
 * 主界面
 * Created by Administrator on 2017/3/17 0017.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

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
        TabPageIndicator ti_home = (TabPageIndicator) view.findViewById(R.id.ti_home);
        ViewPager vp_home = (ViewPager) view.findViewById(R.id.vp_home);
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getChildFragmentManager());
        vp_home.setAdapter(adapter);
        vp_home.setOffscreenPageLimit(4);
        ti_home.setViewPager(vp_home);
        return view;
    }

}
