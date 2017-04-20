package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.HomeNormalRcAdapter;
import com.doubanapp.hbj.douban.adapter.NormalRcAdapter;
import com.doubanapp.hbj.douban.mtitem.ButtomItem;
import com.doubanapp.hbj.douban.mtitem.HomeNormalItem;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.List;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeNormalProvider extends ItemViewProvider<HomeNormalItem, RecyclerView.ViewHolder> {

    private Context mContext;
    private int iconWidth = 50;
    private int iconHeigth = 50;

    public HomeNormalProvider(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.home_normal_rc_item, parent, false);
        HomeNormalItemViewHolder holder = new HomeNormalItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final HomeNormalItem homeNormalItem) {
        ((HomeNormalItemViewHolder) holder).tv_home_normal_type.setText(homeNormalItem.title);
        ((HomeNormalItemViewHolder) holder).tv_home_normal_des.setText(homeNormalItem.res.getResults().getAndroid().get(homeNormalItem.position).getDesc());
        List<String> images = homeNormalItem.res.getResults().getAndroid().get(homeNormalItem.position).getImages();
        if (images != null) {
            Glide.with(mContext)
                    .load(images.get(0))
                    .centerCrop()
                    .crossFade()
                    .override(iconWidth, iconHeigth)
                    .placeholder(R.mipmap.pic_placeholder_default)
                    .error(R.mipmap.pic_placeholder_default)
                    .into(((HomeNormalItemViewHolder) holder).iv_home_normal_image);
        }
    }

    private class HomeNormalItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_home_normal_type;
        private final TextView tv_home_normal_des;
        private final TextView tv_home_normal_who;
        private final TextView tv_home_normal_time;
        private final ImageView iv_home_normal_image;

        public HomeNormalItemViewHolder(View itemView) {
            super(itemView);
            tv_home_normal_type = (TextView) itemView.findViewById(R.id.tv_home_normal_type);
            tv_home_normal_des = (TextView) itemView.findViewById(R.id.tv_home_normal_des);
            tv_home_normal_who = (TextView) itemView.findViewById(R.id.tv_home_normal_who);
            tv_home_normal_time = (TextView) itemView.findViewById(R.id.tv_home_normal_time);
            iv_home_normal_image = (ImageView) itemView.findViewById(R.id.iv_home_normal_image);
        }
    }
}
