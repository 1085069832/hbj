package com.doubanapp.hbj.douban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.TopicActivity;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class MovieListSelectionRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mData = new ArrayList<>();
    private Context mContext;

    public MovieListSelectionRcAdapter(Context mContext, List<String> mData) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.movie_list_selection_rc_item, parent, false);
        MovieItemViewHolder holder = new MovieItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MovieItemViewHolder) holder).tv_movie_list_selection_rc_item.setText(mData.get(position));
        ((MovieItemViewHolder) holder).tv_movie_list_selection_rc_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicActivity.startAction(mContext, position + 3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class MovieItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_movie_list_selection_rc_item;

        public MovieItemViewHolder(View itemView) {
            super(itemView);
            tv_movie_list_selection_rc_item = (TextView) itemView.findViewById(R.id.tv_movie_list_selection_rc_item);
        }
    }
}
