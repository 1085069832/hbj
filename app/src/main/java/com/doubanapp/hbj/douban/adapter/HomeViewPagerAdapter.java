package com.doubanapp.hbj.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.fragment.HomeAllFragment;
import com.doubanapp.hbj.douban.fragment.HomeAndroidFragment;
import com.doubanapp.hbj.douban.fragment.HomeDayRecommendFragment;
import com.doubanapp.hbj.douban.fragment.HomeWelFareFragment;
import com.doubanapp.hbj.douban.utils.MyUtils;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {


    private final String[] title;

    public HomeViewPagerAdapter(FragmentManager fm) {
        super(fm);

        title = MyUtils.getResourcesArrayString(R.array.home_titlepage);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = HomeDayRecommendFragment.newsInstance(position);
        }
        if (position == 1) {
            fragment = HomeAllFragment.newsInstance(position);
        }
        if (position == 2) {
            fragment = HomeAndroidFragment.newsInstance(position);
        }
        if (position == 3) {
            fragment = HomeWelFareFragment.newsInstance(position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return title.length;
    }
}
