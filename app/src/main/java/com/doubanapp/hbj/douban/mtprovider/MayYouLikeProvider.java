package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.TopicActivity;
import com.doubanapp.hbj.douban.adapter.MayYouLikeRcAdapter;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class MayYouLikeProvider extends ItemViewProvider<MayYouLikeItem, RecyclerView.ViewHolder> {

    private Context mContext;

    public MayYouLikeProvider(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.may_you_like_mt_item, parent, false);
        MovieMayYouLikeItemViewHolder holder = new MovieMayYouLikeItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final MayYouLikeItem mayYouLikeItem) {

        ((MovieMayYouLikeItemViewHolder) holder).tv_may_you_like_title.setText(mayYouLikeItem.title);
        MayYouLikeRcAdapter adapter = new MayYouLikeRcAdapter(mayYouLikeItem.content);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        ((MovieMayYouLikeItemViewHolder) holder).rc_may_you_like.setLayoutManager(manager);
        ((MovieMayYouLikeItemViewHolder) holder).rc_may_you_like.setAdapter(adapter);
        ((MovieMayYouLikeItemViewHolder) holder).tv_may_you_like_look_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopicActivity.startAction(mContext, mayYouLikeItem.startIndex);
            }
        });
    }

    private class MovieMayYouLikeItemViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView rc_may_you_like;
        private final TextView tv_may_you_like_title;
        private final TextView tv_may_you_like_look_more;

        public MovieMayYouLikeItemViewHolder(View itemView) {
            super(itemView);
            rc_may_you_like = (RecyclerView) itemView.findViewById(R.id.rc_may_you_like);
            tv_may_you_like_title = (TextView) itemView.findViewById(R.id.tv_may_you_like_title);
            tv_may_you_like_look_more = (TextView) itemView.findViewById(R.id.tv_may_you_like_look_more);

        }
    }
}
