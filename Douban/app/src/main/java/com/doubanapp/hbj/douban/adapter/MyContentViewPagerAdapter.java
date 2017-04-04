package com.doubanapp.hbj.douban.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.doubanapp.hbj.douban.utils.ContentFragmentFactory;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class MyContentViewPagerAdapter extends FragmentStatePagerAdapter {
    public MyContentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ContentFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
