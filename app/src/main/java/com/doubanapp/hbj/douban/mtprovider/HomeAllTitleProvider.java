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
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class HomeAllTitleProvider extends ItemViewProvider<HomeAllTitleItem, RecyclerView.ViewHolder> implements View.OnClickListener {

    private TextView tv_home_all_title;
    private MainActivity mContext;
    private BottomSheetLayout bottomSheet;
    private MenuSheetView menuSheetView;

    public HomeAllTitleProvider(Context mContext) {
        this.mContext = (MainActivity) mContext;
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
        this.bottomSheet = homeAllTitleItem.bottomSheet;
        this.menuSheetView = homeAllTitleItem.menuSheetView;
    }

    @Override
    public synchronized void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_all_title:
                bottomSheet.showWithSheetView(menuSheetView);
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
