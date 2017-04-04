package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.MovieHighlyRecommendViewPagerAdapter;
import com.doubanapp.hbj.douban.item.ContentViewPagerItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class ContentViewPagerProvider extends ItemViewProvider<ContentViewPagerItem, RecyclerView.ViewHolder> {
    private Context mContext;
    private int currentVpItem = 0;

    public ContentViewPagerProvider(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.content_viewpager_mt_item, parent, false);
        MovieItemViewHolder holder = new MovieItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull ContentViewPagerItem movieHighlyRecommendItem) {
        MovieHighlyRecommendViewPagerAdapter adapter = new MovieHighlyRecommendViewPagerAdapter(movieHighlyRecommendItem.mViews);
        ((MovieItemViewHolder) holder).vp_content_viewpager_mt_item.setAdapter(adapter);
        ((MovieItemViewHolder) holder).vp_content_viewpager_mt_item.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        ((MovieItemViewHolder) holder).vp_content_viewpager_mt_item.setCurrentItem((10000 - 10000 % movieHighlyRecommendItem.mViews.size()) + currentVpItem);
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.my_content_viewpager_anim);
        ((MovieItemViewHolder) holder).vp_content_viewpager_mt_item.startAnimation(animation);
        currentVpItem += 1;
    }

    private class MovieItemViewHolder extends RecyclerView.ViewHolder {


        private final ViewPager vp_content_viewpager_mt_item;

        public MovieItemViewHolder(View itemView) {
            super(itemView);
            vp_content_viewpager_mt_item = (ViewPager) itemView.findViewById(R.id.vp_content_viewpager_mt_item);
        }
    }
}
