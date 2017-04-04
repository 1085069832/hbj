package com.doubanapp.hbj.douban.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27 0027.
 */
public class MovieHighlyRecommendViewPagerAdapter extends PagerAdapter {
    private List<View> views = new ArrayList<>();

    public MovieHighlyRecommendViewPagerAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //保证页面只有一个view
        ViewParent parent = views.get(position % views.size()).getParent();
        if (parent != null) {
            container.removeView(views.get(position % views.size()));
        }
        container.addView(views.get(position % views.size()));
        return views.get(position % views.size());
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //覆盖，什么也不写
    }
}
