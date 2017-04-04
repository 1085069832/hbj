package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;

/**
 * 电影 选电影
 * Created by Administrator on 2017/3/26 0026.
 */
public class SelectFragment extends Fragment {

    public static final String TAG = "SelectFragment";
    private static String mSelectTag;//选中的电影标签
    private static int mStartIndex;

    public static SelectFragment newsInstance(int pos, String selectTag) {
        SelectFragment fragment = new SelectFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        mStartIndex = pos;
        mSelectTag = selectTag;
        MyLogUtils.i(TAG, mSelectTag + "");
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView view = new TextView(MyUtils.getContext());
        if (mStartIndex == MyConstants.MOVIE_SELECT_MOVIE_INDEX) {
            view.setText("选电影");
        } else if (mStartIndex == MyConstants.BOOK_SELECT_BOOK_INDEX) {
            view.setText("选图书");
        } else if (mStartIndex == MyConstants.MUSIC_SELECT_MUSIC_INDEX) {
            view.setText("选音乐");
        } else {

        }
        view.setTextColor(Color.GREEN);
        view.setTextSize(35);
        return view;
    }
}
