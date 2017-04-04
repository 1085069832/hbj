package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.TopicActivity;
import com.doubanapp.hbj.douban.adapter.NormalRcAdapter;
import com.doubanapp.hbj.douban.item.NormalItem;
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
        MovieItemViewHolder holder = new MovieItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final NormalItem normalItem) {

        ((MovieItemViewHolder) holder).tv_normal_look_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicActivity.startAction(mContext, normalItem.startIndex);
            }
        });
        ((MovieItemViewHolder) holder).tv_normal_title.setText(normalItem.title);
        NormalRcAdapter adapter = new NormalRcAdapter(normalItem.content);
        LinearLayoutManager manager = new LinearLayoutManager(MyUtils.getContext(), LinearLayoutManager.HORIZONTAL, false);
        ((MovieItemViewHolder) holder).rc_normal_item.setAdapter(adapter);
        ((MovieItemViewHolder) holder).rc_normal_item.setLayoutManager(manager);
    }

    private class MovieItemViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rc_normal_item;
        private final TextView tv_normal_title;
        private final TextView tv_normal_look_more;

        public MovieItemViewHolder(View itemView) {
            super(itemView);
            rc_normal_item = (RecyclerView) itemView.findViewById(R.id.rc_normal_item);
            tv_normal_title = (TextView) itemView.findViewById(R.id.tv_normal_title);
            tv_normal_look_more = (TextView) itemView.findViewById(R.id.tv_normal_look_more);

        }
    }
}
