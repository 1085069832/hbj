package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.mtitem.HomeAllTitleItem;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.mingle.sweetpick.SweetSheet;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeAllTitleProvider extends ItemViewProvider<HomeAllTitleItem, RecyclerView.ViewHolder> implements View.OnClickListener {

    private SweetSheet mSweetSheet;
    private TextView tv_home_all_title;
    private MainActivity mContext;

    public HomeAllTitleProvider(Context mContext, SweetSheet mSweetSheet) {
        this.mContext = (MainActivity) mContext;
        this.mSweetSheet = mSweetSheet;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(MyUtils.getContext()).inflate(R.layout.home_all_title_mt_item, parent, false);
        HomeNormalTitleItemViewHolder holder = new HomeNormalTitleItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final HomeAllTitleItem homeAllTitleItem) {

    }

    @Override
    public synchronized void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_all_title:
                if (!mSweetSheet.isShow()) {
                    mContext.hideBottomNavigation();
                    mContext.hideFloating();
                    mSweetSheet.show();
                }
                break;
            default:
        }
    }

    private class HomeNormalTitleItemViewHolder extends RecyclerView.ViewHolder {

        public HomeNormalTitleItemViewHolder(View itemView) {
            super(itemView);
            tv_home_all_title = (TextView) itemView.findViewById(R.id.tv_home_all_title);
            tv_home_all_title.setOnClickListener(HomeAllTitleProvider.this);
        }
    }
}
