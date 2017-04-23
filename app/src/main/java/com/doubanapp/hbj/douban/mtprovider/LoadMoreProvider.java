package com.doubanapp.hbj.douban.mtprovider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.mtitem.LoadMoreItem;
import com.doubanapp.hbj.douban.utils.MyLogUtils;

import me.drakeet.multitype.ItemViewProvider;

/**
 * 加载更多
 * Created by Administrator on 2017/3/24 0024.
 */
public class LoadMoreProvider extends ItemViewProvider<LoadMoreItem, RecyclerView.ViewHolder> {

    private static final String TAG = "LoadMoreProvider";

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
        MyLogUtils.i(TAG, loadMoreItem.loadMoreState);
        if (loadMoreItem.loadMoreState.equals("上拉加载")) {
            ((LoadMoreItemViewHolder) holder).iv_progressbar_idel.setVisibility(View.VISIBLE);
            ((LoadMoreItemViewHolder) holder).pb_load_more.setVisibility(View.GONE);
        } else if (loadMoreItem.loadMoreState.equals("正在加载")) {
            ((LoadMoreItemViewHolder) holder).iv_progressbar_idel.setVisibility(View.GONE);
            ((LoadMoreItemViewHolder) holder).pb_load_more.setVisibility(View.VISIBLE);
        }
    }

    private class LoadMoreItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_load_more_text;
        private final ImageView iv_progressbar_idel;
        private final ProgressBar pb_load_more;

        public LoadMoreItemViewHolder(View itemView) {
            super(itemView);
            tv_load_more_text = (TextView) itemView.findViewById(R.id.tv_load_more_text);
            iv_progressbar_idel = (ImageView) itemView.findViewById(R.id.iv_progressbar_idel);
            pb_load_more = (ProgressBar) itemView.findViewById(R.id.pb_load_more);
        }
    }
}
