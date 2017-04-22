package com.doubanapp.hbj.douban.adapter;

import android.content.Context;
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
    private Context mContext;
    private List<HomeDayRecommendJsonData.ResultsBean.AndroidBean> homeDayRecommendAndroid;
    private List<HomeDayRecommendJsonData.ResultsBean.IOSBean> homeDayRecommendIos;
    private List<HomeDayRecommendJsonData.ResultsBean.拓展资源Bean> homeDayRecommendExtendsResource;
    private List<HomeDayRecommendJsonData.ResultsBean.前端Bean> homeDayRecommendFront;
    private List<HomeDayRecommendJsonData.ResultsBean.AppBean> homeDayRecommendApp;
    private List<HomeDayRecommendJsonData.ResultsBean.休息视频Bean> homeDayRecommendRest;
    private int iconWidth = 50;
    private int iconHeigth = 50;


    /*
    *HomeDayRecommend数据 */
    public NormalRcAdapter(Context mContext,HomeDayRecommendJsonData mHomeDayRecommendData, int startIndex) {
        this.startIndex = startIndex;
        this.mContext = mContext;
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
            case MyConstants.HOME_DR_FRONT_INDEX:
                homeDayRecommendFront = mHomeDayRecommendData.getResults().get前端();
                break;
            case MyConstants.HOME_DR_APP_INDEX:
                homeDayRecommendApp = mHomeDayRecommendData.getResults().getApp();
                break;
            case MyConstants.HOME_DR_REST_INDEX:
                homeDayRecommendRest = mHomeDayRecommendData.getResults().get休息视频();
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
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("作者: " + homeDayRecommendAndroid.get(position).getWho());
                List<String> androidImages = homeDayRecommendAndroid.get(position).getImages();
                if (androidImages != null) {//有图片
                    Glide.with(mContext)
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
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("作者: " + homeDayRecommendIos.get(position).getWho());
                List<String> iOSImages = homeDayRecommendIos.get(position).getImages();
                if (iOSImages != null) {//有图片
                    Glide.with(mContext)
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
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("作者: " + homeDayRecommendExtendsResource.get(position).getWho());
                List<String> extendsResourceImages = homeDayRecommendExtendsResource.get(position).getImages();
                if (extendsResourceImages != null) {//有图片
                    Glide.with(mContext)
                            .load(extendsResourceImages.get(0))
                            .centerCrop()
                            .crossFade()
                            .placeholder(R.mipmap.pic_placeholder_default)
                            .error(R.mipmap.pic_placeholder_default)
                            .into(((NormalItemViewHolder) holder).iv_normal_rc_item_image);
                }
                break;
            case MyConstants.HOME_DR_FRONT_INDEX://homedayrecommend 前端
                ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(homeDayRecommendFront.get(position).getDesc());
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("作者: " + homeDayRecommendFront.get(position).getWho());
                List<String> frontImages = homeDayRecommendFront.get(position).getImages();
                if (frontImages != null) {//有图片
                    Glide.with(mContext)
                            .load(frontImages.get(0))
                            .centerCrop()
                            .crossFade()
                            .placeholder(R.mipmap.pic_placeholder_default)
                            .error(R.mipmap.pic_placeholder_default)
                            .into(((NormalItemViewHolder) holder).iv_normal_rc_item_image);
                }
                break;
            case MyConstants.HOME_DR_APP_INDEX://homedayrecommend app
                ((NormalItemViewHolder) holder).tv_normal_rc_item_des.setText(homeDayRecommendApp.get(position).getDesc());
                ((NormalItemViewHolder) holder).tv_normal_rc_item_who.setText("作者: " + homeDayRecommendApp.get(position).getWho());
                List<String> appImages = homeDayRecommendApp.get(position).getImages();
                if (appImages != null) {//有图片
                    Glide.with(mContext)
                            .load(appImages.get(0))
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
//        Animation animation = AnimationUtils.loadAnimation(MyUtils.getContext(), R.anim.horizontal_rc_anim);
//        ((NormalItemViewHolder) holder).tv_normal_rc_item_des.startAnimation(animation);

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
        } else if (startIndex == MyConstants.HOME_DR_FRONT_INDEX) {
            count = homeDayRecommendFront.size();
        } else if (startIndex == MyConstants.HOME_DR_APP_INDEX) {
            count = homeDayRecommendApp.size();
        } else if (startIndex == MyConstants.HOME_DR_REST_INDEX) {
            count = homeDayRecommendRest.size();
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
