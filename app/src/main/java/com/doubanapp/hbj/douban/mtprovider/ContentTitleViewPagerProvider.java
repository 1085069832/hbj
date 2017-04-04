package com.doubanapp.hbj.douban.mtprovider;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.ContentTitleViewPagerAdapter;
import com.doubanapp.hbj.douban.mtitem.ContentTitleViewPagerItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class ContentTitleViewPagerProvider extends ItemViewProvider<ContentTitleViewPagerItem, RecyclerView.ViewHolder> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.content_title_vp_mt_item, parent, false);
        TitleViewPagerItemViewHolder holder = new TitleViewPagerItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final ContentTitleViewPagerItem contentTitleViewPagerItem) {
        ContentTitleViewPagerAdapter adapter = new ContentTitleViewPagerAdapter(contentTitleViewPagerItem.mViews);
        ((TitleViewPagerItemViewHolder) holder).vp_content_title_vp_mt_item.setAdapter(adapter);
        ((TitleViewPagerItemViewHolder) holder).vp_content_title_vp_mt_item.setCurrentItem(10000 - 10000 % contentTitleViewPagerItem.mViews.size());
    }

    private class TitleViewPagerItemViewHolder extends RecyclerView.ViewHolder {

        private final ViewPager vp_content_title_vp_mt_item;

        public TitleViewPagerItemViewHolder(View itemView) {
            super(itemView);
            vp_content_title_vp_mt_item = (ViewPager) itemView.findViewById(R.id.vp_content_title_vp_mt_item);
        }
    }
}
