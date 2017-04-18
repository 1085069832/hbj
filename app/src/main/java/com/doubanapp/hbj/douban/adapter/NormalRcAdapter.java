package com.doubanapp.hbj.douban.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 条目适配器
 * Created by Administrator on 2017/3/24 0024.
 */
public class NormalRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> data = new ArrayList<>();
    private int startIndex;
    private List<HomeDayRecommendJsonData.ResultsBean.AndroidBean> homeDayRecommendAndroid;

    /*
    *HomeDayRecommend数据 */
    public NormalRcAdapter(HomeDayRecommendJsonData mHomeDayRecommendData, int startIndex) {
        this.startIndex = startIndex;
        //安卓
        homeDayRecommendAndroid = mHomeDayRecommendData.getResults().getAndroid();
    }

    public NormalRcAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.normal_rc_item, parent, false);
        NormalItemViewHolder holder = new NormalItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if (startIndex == MyConstants.HOME_DR_ANDROID_INDEX) {//homedayrecommend android
            ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(homeDayRecommendAndroid.get(position).getDesc());
            List<String> images = homeDayRecommendAndroid.get(position).getImages();
            if (images != null) {//有图片
                Glide.with(MyUtils.getContext())
                        .load(images.get(0))
                        .centerCrop()
                        .crossFade()
                        .placeholder(R.mipmap.pic_placeholder_default)
                        .error(R.mipmap.pic_placeholder_default)
                        .into(((NormalItemViewHolder) holder).iv_normal_rc_item_image);
            }
        } else {
            ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(data.get(position));
        }
        Animation animation = AnimationUtils.loadAnimation(MyUtils.getContext(), R.anim.horizontal_rc_anim);
        ((NormalItemViewHolder) holder).tv_normal_rc_item_des.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        if (startIndex == MyConstants.HOME_DR_ANDROID_INDEX) {
            return homeDayRecommendAndroid.size();
        } else {
            return data.size();
        }
    }

    private class NormalItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_normal_rc_item_des;
        private final ImageView iv_normal_rc_item_image;

        public NormalItemViewHolder(View itemView) {
            super(itemView);
            tv_normal_rc_item_des = (TextView) itemView.findViewById(R.id.tv_normal_rc_item_des);
            iv_normal_rc_item_image = (ImageView) itemView.findViewById(R.id.iv_normal_rc_item_image);
        }
    }
}
