package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.MovieListSelectionRcAdapter;
import com.doubanapp.hbj.douban.mtitem.MovieListSelectionItem;
import com.doubanapp.hbj.douban.utils.MyUtils;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class MovieListSelectionProvider extends ItemViewProvider<MovieListSelectionItem, RecyclerView.ViewHolder> {
    private Context mContext;

    public MovieListSelectionProvider(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.movie_list_selection_mt_item, parent, false);
        MovieItemViewHolder holder = new MovieItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull MovieListSelectionItem movieListSelectionItem) {
        ((MovieItemViewHolder) holder).tv_movie_list_selection_title.setText(movieListSelectionItem.title);
        MovieListSelectionRcAdapter adapter = new MovieListSelectionRcAdapter(mContext, movieListSelectionItem.content);
        LinearLayoutManager manager = new LinearLayoutManager(MyUtils.getContext(), LinearLayoutManager.HORIZONTAL, false);
        ((MovieItemViewHolder) holder).rc_movie_list_selection_item.setAdapter(adapter);
        ((MovieItemViewHolder) holder).rc_movie_list_selection_item.setLayoutManager(manager);
    }

    private class MovieItemViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rc_movie_list_selection_item;
        private final TextView tv_movie_list_selection_title;

        public MovieItemViewHolder(View itemView) {
            super(itemView);
            rc_movie_list_selection_item = (RecyclerView) itemView.findViewById(R.id.rc_movie_list_selection_item);
            tv_movie_list_selection_title = (TextView) itemView.findViewById(R.id.tv_movie_list_selection_title);

        }
    }
}
