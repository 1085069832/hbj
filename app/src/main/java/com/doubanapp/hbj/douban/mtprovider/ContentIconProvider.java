package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.mtitem.ContentIconItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class ContentIconProvider extends ItemViewProvider<ContentIconItem, RecyclerView.ViewHolder> {
    private Context mContext;

    public ContentIconProvider(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.content_icon_mt_item, parent, false);
        VpItemViewHolder holder = new VpItemViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull final ContentIconItem contentViewPagerItem) {

        if (contentViewPagerItem.startIndex == MyConstants.MOVIE_CONTENT_ICON_INDEX || contentViewPagerItem.startIndex == MyConstants.BOOK_CONTENT_ICON_INDEX) {
            //电影
            //隐藏顶部
            ((VpItemViewHolder) holder).rl_content_icon_mt.setVisibility(View.GONE);
            ((VpItemViewHolder) holder).rl_content_des.setVisibility(View.VISIBLE);
        } else {
            //内容viewpager
            //显示顶部和设置标题
            ((VpItemViewHolder) holder).rl_content_icon_mt.setVisibility(View.VISIBLE);
            ((VpItemViewHolder) holder).rl_content_des.setVisibility(View.GONE);
            ((VpItemViewHolder) holder).tv_content_icon_mt_title.setText(contentViewPagerItem.title);
        }
        //vp点击更多事件
        ((VpItemViewHolder) holder).tv_content_icon_mt_look_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentViewPagerItem.startIndex == MyConstants.HOME_CONTENT_MORE_RECOMMEND_ICON_INDEX) {
                    //更多推荐
                    Toast.makeText(mContext, "点击了更多推荐", Toast.LENGTH_SHORT).show();
                } else if (contentViewPagerItem.startIndex == MyConstants.HOME_CONTENT_REST_ICON_INDEX) {
                    //更多休息视频
                    Toast.makeText(mContext, "点击了更多休息视频", Toast.LENGTH_SHORT).show();
                } else if (contentViewPagerItem.startIndex == MyConstants.HOME_CONTENT_WELFARE_ICON_INDEX) {
                    //更多福利
                    Toast.makeText(mContext, "点击了更多福利", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((VpItemViewHolder) holder).iv_content_icon_mt_item.setBackgroundResource(R.mipmap.navigation_title_icon);

    }

    private class VpItemViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_content_icon_mt_item;
        private final TextView tv_content_icon_mt_title;
        private final TextView tv_content_icon_mt_look_more;
        private final RelativeLayout rl_content_icon_mt;
        private final RelativeLayout rl_content_des;

        public VpItemViewHolder(View itemView) {
            super(itemView);
            iv_content_icon_mt_item = (ImageView) itemView.findViewById(R.id.iv_content_icon_mt_item);
            tv_content_icon_mt_title = (TextView) itemView.findViewById(R.id.tv_content_icon_mt_title);
            tv_content_icon_mt_look_more = (TextView) itemView.findViewById(R.id.tv_content_icon_mt_look_more);
            rl_content_icon_mt = (RelativeLayout) itemView.findViewById(R.id.rl_content_icon_mt);
            rl_content_des = (RelativeLayout) itemView.findViewById(R.id.rl_content_des);
        }
    }
}
