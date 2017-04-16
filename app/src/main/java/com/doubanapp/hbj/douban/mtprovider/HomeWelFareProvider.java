package com.doubanapp.hbj.douban.mtprovider;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.mtitem.HomeWelFareItem;
import com.doubanapp.hbj.douban.utils.MyUtils;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeWelFareProvider extends ItemViewProvider<HomeWelFareItem, RecyclerView.ViewHolder> {

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.welfare_rc_item, parent, false);
        HomeWelFareItemViewHolder holder = new HomeWelFareItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final HomeWelFareItem homeWelFareItem) {
        ViewGroup.LayoutParams params = ((HomeWelFareItemViewHolder) holder).ll_welfare_rc_item.getLayoutParams();
        params.height = homeWelFareItem.addHeight;
        ((HomeWelFareItemViewHolder) holder).ll_welfare_rc_item.setLayoutParams(params);
    }

    private class HomeWelFareItemViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout ll_welfare_rc_item;
        private final ImageView iv_welfare_rc_item;

        public HomeWelFareItemViewHolder(View itemView) {
            super(itemView);
            ll_welfare_rc_item = (LinearLayout) itemView.findViewById(R.id.ll_welfare_rc_item);
            iv_welfare_rc_item = (ImageView) itemView.findViewById(R.id.iv_welfare_rc_item);
        }
    }
}
