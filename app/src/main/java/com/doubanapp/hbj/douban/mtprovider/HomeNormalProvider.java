package com.doubanapp.hbj.douban.mtprovider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.HomeNormalRcAdapter;
import com.doubanapp.hbj.douban.mtitem.ButtomItem;
import com.doubanapp.hbj.douban.mtitem.HomeNormalItem;
import com.doubanapp.hbj.douban.utils.MyUtils;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeNormalProvider extends ItemViewProvider<HomeNormalItem, RecyclerView.ViewHolder> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.home_normal_rc_item, parent, false);
        HomeNormalItemViewHolder holder = new HomeNormalItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final HomeNormalItem homeNormalItem) {

    }

    private class HomeNormalItemViewHolder extends RecyclerView.ViewHolder {

        public HomeNormalItemViewHolder(View itemView) {
            super(itemView);

        }
    }
}
