package com.doubanapp.hbj.douban.mtprovider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.TopicActivity;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.mtitem.SelectItem;

import me.drakeet.multitype.ItemViewProvider;

/**
 * Created by Administrator on 2017/3/24 0024.
 */
public class SelectProvider extends ItemViewProvider<SelectItem, RecyclerView.ViewHolder> {

    private String selectTag;//标记选中的电影标签
    private int startIndex;//标记是哪个页面进入的
    private Context mContext;

    public SelectProvider(Context mContext, int startIndex) {
        this.mContext = mContext;
        this.startIndex = startIndex;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view;
        if (startIndex == MyConstants.MOVIE_SELECT_MOVIE_INDEX) {
            view = inflater.inflate(R.layout.movie_select_mt_item, parent, false);
        } else if (startIndex == MyConstants.BOOK_SELECT_BOOK_INDEX) {
            view = inflater.inflate(R.layout.book_select_mt_item, parent, false);
        } else if (startIndex == MyConstants.MUSIC_SELECT_MUSIC_INDEX) {
            view = inflater.inflate(R.layout.music_select_mt_item, parent, false);
        } else {
            view = null;
        }
        MovieSelectViewHolder holder = new MovieSelectViewHolder(view);
        return holder;
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @NonNull SelectItem movieHotItem) {

        if (startIndex == MyConstants.MOVIE_SELECT_MOVIE_INDEX) {
            ((MovieSelectViewHolder) holder).tv_movie_select_title.setText(movieHotItem.title);
        } else if (startIndex == MyConstants.BOOK_SELECT_BOOK_INDEX) {
            ((MovieSelectViewHolder) holder).tv_book_select_title.setText(movieHotItem.title);
        } else if (startIndex == MyConstants.MUSIC_SELECT_MUSIC_INDEX) {
            ((MovieSelectViewHolder) holder).tv_music_select_title.setText(movieHotItem.title);
        } else {

        }
    }

    private class MovieSelectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_movie_select_title;
        private TextView tv_book_select_title;
        private TextView tv_music_select_title;

        public MovieSelectViewHolder(View itemView) {
            super(itemView);
            if (startIndex == MyConstants.MOVIE_SELECT_MOVIE_INDEX) {
                tv_movie_select_title = (TextView) itemView.findViewById(R.id.tv_movie_select_title);
                TextView tv_movie_jd_select_item = (TextView) itemView.findViewById(R.id.tv_movie_jd_select_item);
                TextView tv_movie_dbgf_select_item = (TextView) itemView.findViewById(R.id.tv_movie_dbgf_select_item);
                TextView tv_movie_lmjp_select_item = (TextView) itemView.findViewById(R.id.tv_movie_lmjp_select_item);
                TextView tv_movie_jq_select_item = (TextView) itemView.findViewById(R.id.tv_movie_jq_select_item);
                TextView tv_movie_aq_select_item = (TextView) itemView.findViewById(R.id.tv_movie_aq_select_item);
                TextView tv_movie_xj_select_item = (TextView) itemView.findViewById(R.id.tv_movie_xj_select_item);
                TextView tv_movie_kh_select_item = (TextView) itemView.findViewById(R.id.tv_movie_kh_select_item);
                TextView tv_movie_dz_select_item = (TextView) itemView.findViewById(R.id.tv_movie_dz_select_item);
                TextView tv_movie_xy_select_item = (TextView) itemView.findViewById(R.id.tv_movie_xy_select_item);
                TextView tv_movie_zy_select_item = (TextView) itemView.findViewById(R.id.tv_movie_zy_select_item);
                TextView tv_movie_qc_select_item = (TextView) itemView.findViewById(R.id.tv_movie_qc_select_item);
                TextView tv_movie_wy_select_item = (TextView) itemView.findViewById(R.id.tv_movie_wy_select_item);
                TextView tv_movie_rb_select_item = (TextView) itemView.findViewById(R.id.tv_movie_rb_select_item);
                TextView tv_movie_hg_select_item = (TextView) itemView.findViewById(R.id.tv_movie_hg_select_item);
                tv_movie_jd_select_item.setOnClickListener(this);
                tv_movie_dbgf_select_item.setOnClickListener(this);
                tv_movie_lmjp_select_item.setOnClickListener(this);
                tv_movie_jq_select_item.setOnClickListener(this);
                tv_movie_aq_select_item.setOnClickListener(this);
                tv_movie_xj_select_item.setOnClickListener(this);
                tv_movie_kh_select_item.setOnClickListener(this);
                tv_movie_dz_select_item.setOnClickListener(this);
                tv_movie_xy_select_item.setOnClickListener(this);
                tv_movie_zy_select_item.setOnClickListener(this);
                tv_movie_qc_select_item.setOnClickListener(this);
                tv_movie_wy_select_item.setOnClickListener(this);
                tv_movie_rb_select_item.setOnClickListener(this);
                tv_movie_hg_select_item.setOnClickListener(this);
            } else if (startIndex == MyConstants.BOOK_SELECT_BOOK_INDEX) {
                tv_book_select_title = (TextView) itemView.findViewById(R.id.tv_book_select_title);
                TextView tv_book_xs_select_item = (TextView) itemView.findViewById(R.id.tv_book_xs_select_item);
                TextView tv_book_aq_select_item = (TextView) itemView.findViewById(R.id.tv_book_aq_select_item);
                TextView tv_book_ls_select_item = (TextView) itemView.findViewById(R.id.tv_book_ls_select_item);
                TextView tv_book_wgwz_select_item = (TextView) itemView.findViewById(R.id.tv_book_wgwz_select_item);
                TextView tv_book_qc_select_item = (TextView) itemView.findViewById(R.id.tv_book_qc_select_item);
                TextView tv_book_lz_select_item = (TextView) itemView.findViewById(R.id.tv_book_lz_select_item);
                TextView tv_book_sb_select_item = (TextView) itemView.findViewById(R.id.tv_book_sb_select_item);
                TextView tv_book_zj_select_item = (TextView) itemView.findViewById(R.id.tv_book_zj_select_item);
                TextView tv_book_tl_select_item = (TextView) itemView.findViewById(R.id.tv_book_tl_select_item);
                TextView tv_book_lx_select_item = (TextView) itemView.findViewById(R.id.tv_book_lx_select_item);
                TextView tv_book_qh_select_item = (TextView) itemView.findViewById(R.id.tv_book_qh_select_item);
                TextView tv_book_jg_select_item = (TextView) itemView.findViewById(R.id.tv_book_jg_select_item);
                tv_book_xs_select_item.setOnClickListener(this);
                tv_book_aq_select_item.setOnClickListener(this);
                tv_book_ls_select_item.setOnClickListener(this);
                tv_book_wgwz_select_item.setOnClickListener(this);
                tv_book_qc_select_item.setOnClickListener(this);
                tv_book_lz_select_item.setOnClickListener(this);
                tv_book_sb_select_item.setOnClickListener(this);
                tv_book_zj_select_item.setOnClickListener(this);
                tv_book_tl_select_item.setOnClickListener(this);
                tv_book_lx_select_item.setOnClickListener(this);
                tv_book_qh_select_item.setOnClickListener(this);
                tv_book_jg_select_item.setOnClickListener(this);
            } else if (startIndex == MyConstants.MUSIC_SELECT_MUSIC_INDEX) {
                tv_music_select_title = (TextView) itemView.findViewById(R.id.tv_music_select_title);
                TextView tv_music_lx_select_item = (TextView) itemView.findViewById(R.id.tv_music_lx_select_item);
                TextView tv_music_yg_select_item = (TextView) itemView.findViewById(R.id.tv_music_yg_select_item);
                TextView tv_music_my_select_item = (TextView) itemView.findViewById(R.id.tv_music_my_select_item);
                TextView tv_music_dl_select_item = (TextView) itemView.findViewById(R.id.tv_music_dl_select_item);
                TextView tv_music_hy_select_item = (TextView) itemView.findViewById(R.id.tv_music_hy_select_item);
                TextView tv_music_om_select_item = (TextView) itemView.findViewById(R.id.tv_music_om_select_item);
                TextView tv_music_rb_select_item = (TextView) itemView.findViewById(R.id.tv_music_rb_select_item);
                TextView tv_music_hg_select_item = (TextView) itemView.findViewById(R.id.tv_music_hg_select_item);
                tv_music_lx_select_item.setOnClickListener(this);
                tv_music_yg_select_item.setOnClickListener(this);
                tv_music_my_select_item.setOnClickListener(this);
                tv_music_dl_select_item.setOnClickListener(this);
                tv_music_hy_select_item.setOnClickListener(this);
                tv_music_om_select_item.setOnClickListener(this);
                tv_music_rb_select_item.setOnClickListener(this);
                tv_music_hg_select_item.setOnClickListener(this);
            } else {

            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                //电影
                case R.id.tv_movie_jd_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_JD_INDEX;
                    break;
                case R.id.tv_movie_dbgf_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_DBGF_INDEX;
                    break;
                case R.id.tv_movie_lmjp_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_LMJP_INDEX;
                    break;
                case R.id.tv_movie_jq_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_JQ_INDEX;
                    break;
                case R.id.tv_movie_aq_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_AQ_INDEX;
                    break;
                case R.id.tv_movie_xj_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_XJ_INDEX;
                    break;
                case R.id.tv_movie_kh_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_KH_INDEX;
                    break;
                case R.id.tv_movie_dz_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_DZ_INDEX;
                    break;
                case R.id.tv_movie_xy_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_XY_INDEX;
                    break;
                case R.id.tv_movie_zy_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_ZY_INDEX;
                    break;
                case R.id.tv_movie_qc_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_QC_INDEX;
                    break;
                case R.id.tv_movie_wy_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_WY_INDEX;
                    break;
                case R.id.tv_movie_rb_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_RB_INDEX;
                    break;
                case R.id.tv_movie_hg_select_item:
                    selectTag = MyConstants.MOVIE_SELECT_HG_INDEX;
                    break;
                //书籍
                case R.id.tv_book_xs_select_item:
                    selectTag = MyConstants.BOOK_SELECT_XS_INDEX;
                    break;
                case R.id.tv_book_aq_select_item:
                    selectTag = MyConstants.BOOK_SELECT_AQ_INDEX;
                    break;
                case R.id.tv_book_ls_select_item:
                    selectTag = MyConstants.BOOK_SELECT_LS_INDEX;
                    break;
                case R.id.tv_book_wgwz_select_item:
                    selectTag = MyConstants.BOOK_SELECT_WGWZ_INDEX;
                    break;
                case R.id.tv_book_qc_select_item:
                    selectTag = MyConstants.BOOK_SELECT_QC_INDEX;
                    break;
                case R.id.tv_book_lz_select_item:
                    selectTag = MyConstants.BOOK_SELECT_LZ_INDEX;
                    break;
                case R.id.tv_book_sb_select_item:
                    selectTag = MyConstants.BOOK_SELECT_SB_INDEX;
                    break;
                case R.id.tv_book_zj_select_item:
                    selectTag = MyConstants.BOOK_SELECT_ZJ_INDEX;
                    break;
                case R.id.tv_book_tl_select_item:
                    selectTag = MyConstants.BOOK_SELECT_TL_INDEX;
                    break;
                case R.id.tv_book_lx_select_item:
                    selectTag = MyConstants.BOOK_SELECT_LX_INDEX;
                    break;
                case R.id.tv_book_qh_select_item:
                    selectTag = MyConstants.BOOK_SELECT_QH_INDEX;
                    break;
                case R.id.tv_book_jg_select_item:
                    selectTag = MyConstants.BOOK_SELECT_JG_INDEX;
                    break;
                //音乐
                case R.id.tv_music_lx_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_LX_INDEX;
                    break;
                case R.id.tv_music_yg_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_YG_INDEX;
                    break;
                case R.id.tv_music_my_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_MY_INDEX;
                    break;
                case R.id.tv_music_dl_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_DL_INDEX;
                    break;
                case R.id.tv_music_hy_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_HY_INDEX;
                    break;
                case R.id.tv_music_om_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_OM_INDEX;
                    break;
                case R.id.tv_music_rb_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_RB_INDEX;
                    break;
                case R.id.tv_music_hg_select_item:
                    selectTag = MyConstants.MUSIC_SELECT_HG_INDEX;
                    break;

                default:
            }

            if (startIndex == MyConstants.MOVIE_SELECT_MOVIE_INDEX) {
                TopicActivity.startAction(mContext, MyConstants.MOVIE_SELECT_MOVIE_INDEX, selectTag);
            } else if (startIndex == MyConstants.BOOK_SELECT_BOOK_INDEX) {
                TopicActivity.startAction(mContext, MyConstants.BOOK_SELECT_BOOK_INDEX, selectTag);
            } else {
                TopicActivity.startAction(mContext, MyConstants.MUSIC_SELECT_MUSIC_INDEX, selectTag);
            }

        }
    }
}
