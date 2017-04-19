package com.doubanapp.hbj.douban.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 条目适配器
 * Created by Administrator on 2017/3/24 0024.
 */
public class NormalRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String TAG = "NormalRcAdapter";
    private List<String> data = new ArrayList<>();
    private int startIndex;
    private List<HomeDayRecommendJsonData.ResultsBean.AndroidBean> homeDayRecommendAndroid;
    private List<HomeDayRecommendJsonData.ResultsBean.IOSBean> homeDayRecommendIos;
    private List<HomeDayRecommendJsonData.ResultsBean.拓展资源Bean> homeDayRecommendExtendsResource;

    /*
    *HomeDayRecommend数据 */
    public NormalRcAdapter(HomeDayRecommendJsonData mHomeDayRecommendData, int startIndex) {
        this.startIndex = startIndex;
        switch (startIndex) {
            case MyConstants.HOME_DR_ANDROID_INDEX:
                homeDayRecommendAndroid = mHomeDayRecommendData.getResults().getAndroid();
                MyLogUtils.i(TAG, homeDayRecommendAndroid.size() + "");
                break;
            case MyConstants.HOME_DR_IOS_INDEX:
                homeDayRecommendIos = mHomeDayRecommendData.getResults().getIOS();
                MyLogUtils.i(TAG, homeDayRecommendIos.size() + "");
                break;
            case MyConstants.HOME_DR_EXTENDS_RESOURCE_INDEX:
                homeDayRecommendExtendsResource = mHomeDayRecommendData.getResults().get拓展资源();
                break;
            default:
        }
    }

    public NormalRcAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.normal_rc_item, parent, false);
        NormalItemViewHolder holder = new NormalItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (startIndex) {
            case MyConstants.HOME_DR_ANDROID_INDEX://homedayrecommend android
                ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(homeDayRecommendAndroid.get(position).getDesc());
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("-- -- " + homeDayRecommendAndroid.get(position).getWho());
                List<String> androidImages = homeDayRecommendAndroid.get(position).getImages();
                if (androidImages != null) {//有图片
                    Glide.with(MyUtils.getContext())
                            .load(androidImages.get(0))
                            .centerCrop()
                            .crossFade()
                            .placeholder(R.mipmap.pic_placeholder_default)
                            .error(R.mipmap.pic_placeholder_default)
                            .into(((NormalItemViewHolder) holder).iv_normal_rc_item_image);
                }
                break;
            case MyConstants.HOME_DR_IOS_INDEX://homedayrecommend ios
                ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(homeDayRecommendIos.get(position).getDesc());
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("-- -- " + homeDayRecommendIos.get(position).getWho());
                List<String> iOSImages = homeDayRecommendIos.get(position).getImages();
                if (iOSImages != null) {//有图片
                    Glide.with(MyUtils.getContext())
                            .load(iOSImages.get(0))
                            .centerCrop()
                            .crossFade()
                            .placeholder(R.mipmap.pic_placeholder_default)
                            .error(R.mipmap.pic_placeholder_default)
                            .into(((NormalItemViewHolder) holder).iv_normal_rc_item_image);
                }
                break;
            case MyConstants.HOME_DR_EXTENDS_RESOURCE_INDEX://homedayrecommend 扩展资源
                ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(homeDayRecommendExtendsResource.get(position).getDesc());
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("-- -- " + homeDayRecommendExtendsResource.get(position).getWho());
                List<String> ExtendsResourceImages = homeDayRecommendExtendsResource.get(position).getImages();
                if (ExtendsResourceImages != null) {//有图片
                    Glide.with(MyUtils.getContext())
                            .load(ExtendsResourceImages.get(0))
                            .centerCrop()
                            .crossFade()
                            .placeholder(R.mipmap.pic_placeholder_default)
                            .error(R.mipmap.pic_placeholder_default)
                            .into(((NormalItemViewHolder) holder).iv_normal_rc_item_image);
                }
                break;
            default:
                ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(data.get(position));
        }
        Animation animation = AnimationUtils.loadAnimation(MyUtils.getContext(), R.anim.horizontal_rc_anim);
        ((NormalItemViewHolder) holder).tv_normal_rc_item_des.startAnimation(animation);

    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (startIndex == MyConstants.HOME_DR_ANDROID_INDEX) {
            count = homeDayRecommendAndroid.size();
        } else if (startIndex == MyConstants.HOME_DR_IOS_INDEX) {
            count = homeDayRecommendIos.size();
        } else if (startIndex == MyConstants.HOME_DR_EXTENDS_RESOURCE_INDEX) {
            count = homeDayRecommendExtendsResource.size();
            MyLogUtils.i(TAG, "ExtendsResource" + count);
        } else {
            count = 0;
        }
        return count;
    }

    private class NormalItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_normal_rc_item_des;
        private final TextView tv_normal_rc_item_who;
        private final ImageView iv_normal_rc_item_image;

        public NormalItemViewHolder(View itemView) {
            super(itemView);
            tv_normal_rc_item_des = (TextView) itemView.findViewById(R.id.tv_normal_rc_item_des);
            tv_normal_rc_item_who = (TextView) itemView.findViewById(R.id.tv_normal_rc_item_who);
            iv_normal_rc_item_image = (ImageView) itemView.findViewById(R.id.iv_normal_rc_item_image);
        }
    }
}
