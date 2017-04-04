package com.doubanapp.hbj.douban.mtprovider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.mtitem.ButtomItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class ButtomProvider extends ItemViewProvider<ButtomItem, RecyclerView.ViewHolder> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.buttom_mt_item, parent, false);
        ButtomItemViewHolder holder = new ButtomItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final ButtomItem buttomItem) {

    }

    private class ButtomItemViewHolder extends RecyclerView.ViewHolder {

        public ButtomItemViewHolder(View itemView) {
            super(itemView);

        }
    }
}
