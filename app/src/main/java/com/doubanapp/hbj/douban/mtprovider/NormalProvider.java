package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.TopicActivity;
import com.doubanapp.hbj.douban.adapter.NormalRcAdapter;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.utils.MyUtils;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class NormalProvider extends ItemViewProvider<NormalItem, RecyclerView.ViewHolder> {
    private Context mContext;

    public NormalProvider(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.normal_mt_item, parent, false);
        NormalItemViewHolder holder = new NormalItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final NormalItem normalItem) {

        //点击更多监听
        ((NormalItemViewHolder) holder).tv_normal_look_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (normalItem.startIndex == MyConstants.HOME_DR_ANDROID_INDEX) {
                    Toast.makeText(mContext, "点击了ANDROID", Toast.LENGTH_SHORT).show();
                } else if (normalItem.startIndex == MyConstants.HOME_DR_IOS_INDEX) {
                    Toast.makeText(mContext, "点击了IOS", Toast.LENGTH_SHORT).show();
                } else if (normalItem.startIndex == MyConstants.HOME_DR_FRONT_INDEX) {
                    Toast.makeText(mContext, "点击了前端", Toast.LENGTH_SHORT).show();
                } else if (normalItem.startIndex == MyConstants.HOME_DR_APP_INDEX) {
                    Toast.makeText(mContext, "点击了APP", Toast.LENGTH_SHORT).show();
                } else {
                    TopicActivity.startAction(mContext, normalItem.startIndex);
                }
            }
        });
        ((NormalItemViewHolder) holder).tv_normal_title.setText(normalItem.title);
        //设置适配器
        NormalRcAdapter adapter;
        switch (normalItem.startIndex) {//homedayrecommend android
            case MyConstants.HOME_DR_ANDROID_INDEX:
                ((NormalItemViewHolder) holder).tv_normal_title.setText(normalItem.title);
                adapter = new NormalRcAdapter(normalItem.rbContent, MyConstants.HOME_DR_ANDROID_INDEX);
                break;
            default:
                adapter = new NormalRcAdapter(normalItem.content);
        }

        LinearLayoutManager manager = new LinearLayoutManager(MyUtils.getContext(), LinearLayoutManager.HORIZONTAL, false);
        ((NormalItemViewHolder) holder).rc_normal_item.setLayoutManager(manager);
        ((NormalItemViewHolder) holder).rc_normal_item.setAdapter(adapter);
    }

    private class NormalItemViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rc_normal_item;
        private final TextView tv_normal_title;
        private final TextView tv_normal_look_more;

        public NormalItemViewHolder(View itemView) {
            super(itemView);
            rc_normal_item = (RecyclerView) itemView.findViewById(R.id.rc_normal_item);
            tv_normal_title = (TextView) itemView.findViewById(R.id.tv_normal_title);
            tv_normal_look_more = (TextView) itemView.findViewById(R.id.tv_normal_look_more);

        }
    }
}
