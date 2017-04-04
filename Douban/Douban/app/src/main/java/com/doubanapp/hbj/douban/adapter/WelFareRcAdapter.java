package com.doubanapp.hbj.douban.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class WelFareRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int initHeight = 260;
    private List<String> mData = new ArrayList<>();
    private List<Integer> mHeight = new ArrayList<>();

    public WelFareRcAdapter(List<String> mData, List<Integer> mHeight) {
        this.mData = mData;
        this.mHeight = mHeight;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.welfare_rc_item, parent, false);
        WelFareItemViewHolder holder = new WelFareItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //((WelFareItemViewHolder) holder).iv_welfare_rc_item.setImageResource(R.mipmap.ic_launcher);

        ViewGroup.LayoutParams params = ((WelFareItemViewHolder) holder).ll_welfare_rc_item.getLayoutParams();
        params.height = initHeight + mHeight.get(position);
        ((WelFareItemViewHolder) holder).ll_welfare_rc_item.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class WelFareItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_welfare_rc_item;
        private final LinearLayout ll_welfare_rc_item;

        public WelFareItemViewHolder(View itemView) {
            super(itemView);
            ll_welfare_rc_item = (LinearLayout) itemView.findViewById(R.id.ll_welfare_rc_item);
            iv_welfare_rc_item = (ImageView) itemView.findViewById(R.id.iv_welfare_rc_item);
        }
    }
}
