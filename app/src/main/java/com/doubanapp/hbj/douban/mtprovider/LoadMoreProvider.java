package com.doubanapp.hbj.douban.mtprovider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.mtitem.LoadMoreItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class LoadMoreProvider extends ItemViewProvider<LoadMoreItem, RecyclerView.ViewHolder> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.load_more_mt_item, parent, false);
        LoadMoreItemViewHolder holder = new LoadMoreItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final LoadMoreItem loadMoreItem) {
        ((LoadMoreItemViewHolder) holder).tv_load_more_text.setText(loadMoreItem.loadMoreState);
    }

    private class LoadMoreItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_load_more_text;

        public LoadMoreItemViewHolder(View itemView) {
            super(itemView);
            tv_load_more_text = (TextView) itemView.findViewById(R.id.tv_load_more_text);
        }
    }
}
