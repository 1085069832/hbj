package com.doubanapp.hbj.douban.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class NormalRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private HomeDayRecommendJsonData.ResultsBean mData;
    private List<String> data = new ArrayList<>();
    private int startIndex;

    /*
    *HomeDayRecommend数据 */
    public NormalRcAdapter(HomeDayRecommendJsonData.ResultsBean mData, int startIndex) {
        this.mData = mData;
        this.startIndex = startIndex;
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
            ((NormalItemViewHolder) holder).tv_normal_rc_item.setText(mData.getAndroid().get(position).getWho());
        } else {
            ((NormalItemViewHolder) holder).tv_normal_rc_item.setText(data.get(position));
        }
        Animation animation = AnimationUtils.loadAnimation(MyUtils.getContext(), R.anim.horizontal_rc_anim);
        ((NormalItemViewHolder) holder).tv_normal_rc_item.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        if (startIndex == MyConstants.HOME_DR_ANDROID_INDEX) {
            return mData.getAndroid().size();
        } else {
            return data.size();
        }
    }

    private class NormalItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_normal_rc_item;

        public NormalItemViewHolder(View itemView) {
            super(itemView);
            tv_normal_rc_item = (TextView) itemView.findViewById(R.id.tv_normal_rc_item);
        }
    }
}
