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
public class MayYouLikeRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> mData = new ArrayList<>();

    public MayYouLikeRcAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.may_you_like_rc_item, parent, false);
        MovieItemViewHolder holder = new MovieItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MovieItemViewHolder) holder).tv_may_you_like_rc_item.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class MovieItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_may_you_like_rc_item;

        public MovieItemViewHolder(View itemView) {
            super(itemView);
            tv_may_you_like_rc_item = (TextView) itemView.findViewById(R.id.tv_may_you_like_rc_item);
        }
    }
}
