package com.doubanapp.hbj.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.doubanapp.hbj.douban.fragment.MovieComingSoonFragment;
import com.doubanapp.hbj.douban.fragment.MovieHotShowFragment;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class MovieHotShowAndComingSoonViewPagerAdapter extends FragmentPagerAdapter {
    public MovieHotShowAndComingSoonViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "正在热映";
        } else if (position == 1) {
            return "即将上映";
        } else {
            return "";
        }
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MovieHotShowFragment.newsInstance(position);
        } else if (position == 1) {
            return MovieComingSoonFragment.newsInstance(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
