package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.HighlyRecommendViewPagerAdapter;
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
        VpItemViewHolder holder = new VpItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull ContentViewPagerItem contentViewPagerItem) {
        ((VpItemViewHolder) holder).tv_content_vp_mt_title.setText(contentViewPagerItem.title);

        HighlyRecommendViewPagerAdapter adapter = new HighlyRecommendViewPagerAdapter(contentViewPagerItem.mViews);
        ((VpItemViewHolder) holder).vp_content_viewpager_mt_item.setAdapter(adapter);
        /*((VpItemViewHolder) holder).vp_content_viewpager_mt_item.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        ((VpItemViewHolder) holder).vp_content_viewpager_mt_item.setCurrentItem(currentVpItem % contentViewPagerItem.mViews.size());
        Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.my_content_viewpager_anim);
        ((VpItemViewHolder) holder).vp_content_viewpager_mt_item.startAnimation(animation);*/
        currentVpItem += 1;
    }

    private class VpItemViewHolder extends RecyclerView.ViewHolder {

        private final ViewPager vp_content_viewpager_mt_item;
        private final TextView tv_content_vp_mt_title;
        private final TextView tv_content_vp_mt_look_more;

        public VpItemViewHolder(View itemView) {
            super(itemView);
            vp_content_viewpager_mt_item = (ViewPager) itemView.findViewById(R.id.vp_content_viewpager_mt_item);
            tv_content_vp_mt_title = (TextView) itemView.findViewById(R.id.tv_content_vp_mt_title);
            tv_content_vp_mt_look_more = (TextView) itemView.findViewById(R.id.tv_content_vp_mt_look_more);
        }
    }
}
