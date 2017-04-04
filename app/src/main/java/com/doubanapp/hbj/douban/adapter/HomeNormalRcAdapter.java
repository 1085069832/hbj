package com.doubanapp.hbj.douban.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeNormalRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mData = new ArrayList<>();

    public HomeNormalRcAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.home_normal_rc_item, parent, false);
        HomeNormalItemViewHolder holder = new HomeNormalItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((HomeNormalItemViewHolder) holder).tv_home_normal_type.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class HomeNormalItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_home_normal_type;

        public HomeNormalItemViewHolder(View itemView) {
            super(itemView);
            tv_home_normal_type = (TextView) itemView.findViewById(R.id.tv_home_normal_type);
        }
    }
}
