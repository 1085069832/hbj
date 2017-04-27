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
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.mtitem.HomeNormalItem;
import com.doubanapp.hbj.douban.utils.MyUtils;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeNormalProvider extends ItemViewProvider<HomeNormalItem, RecyclerView.ViewHolder> {

    private Context mContext;

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
        Glide.with(mContext)
                .load(R.mipmap.pic_placeholder_default)
                .centerCrop()
                .into(((HomeNormalItemViewHolder) holder).iv_home_normal_image);
        switch (homeNormalItem.pageIndex) {
            case MyConstants.HOME_DR_REST_INDEX://homerecommend 休息视频
                ((HomeNormalItemViewHolder) holder).tv_home_normal_type.setText(homeNormalItem.title);
                HomeDayRecommendJsonData.ResultsBean.休息视频Bean rest = homeNormalItem.homeDayRecommendJsonDataRes.getResults().get休息视频().get(0);
                ((HomeNormalItemViewHolder) holder).tv_home_normal_who.setText("作者: " + rest.getWho());
                String time = rest.getPublishedAt().replace("T", " ");
                String substringTime = time.substring(time.indexOf("-") + 1, time.lastIndexOf(":"));
                ((HomeNormalItemViewHolder) holder).tv_home_normal_time.setText(substringTime);
                ((HomeNormalItemViewHolder) holder).tv_home_normal_des.setText(rest.getDesc());
                break;
            case MyConstants.HOME_ALL_PRESENTER_PAGE_INDEX://home all
                ((HomeNormalItemViewHolder) holder).tv_home_normal_type.setText(homeNormalItem.type);
                ((HomeNormalItemViewHolder) holder).tv_home_normal_who.setText("作者: " + homeNormalItem.who);
                String homeJsonTime = homeNormalItem.time.replace("T", " ");
                String homeJsonTimest = homeJsonTime.substring(homeJsonTime.indexOf("-") + 1, homeJsonTime.lastIndexOf(":"));
                ((HomeNormalItemViewHolder) holder).tv_home_normal_time.setText(homeJsonTimest);
                ((HomeNormalItemViewHolder) holder).tv_home_normal_des.setText(homeNormalItem.des);
                if (homeNormalItem.image != null) {
                    Glide.with(mContext)
                            .load(homeNormalItem.image)
                            .centerCrop()
                            .crossFade()
                            .placeholder(R.mipmap.pic_placeholder_default)
                            .error(R.mipmap.ic_loading_error)
                            .into(((HomeNormalItemViewHolder) holder).iv_home_normal_image);
                }
                break;
            default:
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
