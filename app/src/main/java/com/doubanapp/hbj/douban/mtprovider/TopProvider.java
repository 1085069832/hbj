package com.doubanapp.hbj.douban.mtprovider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.mtitem.TopItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class TopProvider extends ItemViewProvider<TopItem, RecyclerView.ViewHolder> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.buttom_mt_item, parent, false);
        ButtomItemViewHolder holder = new ButtomItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final TopItem topItem) {
        String day = topItem.day.replace("/", "月");
        ((ButtomItemViewHolder) holder).tv_day.setText(day.substring(5) + "日");
        ((ButtomItemViewHolder) holder).tv_year.setText(day.substring(0, day.indexOf("月")));
    }

    private class ButtomItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_day;
        private final TextView tv_year;

        public ButtomItemViewHolder(View itemView) {
            super(itemView);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
            tv_year = (TextView) itemView.findViewById(R.id.tv_year);
        }
    }
}
