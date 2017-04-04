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
import com.doubanapp.hbj.douban.utils.MyUtils;

/**
 * 电影 你可能喜欢
 * Created by Administrator on 2017/3/26 0026.
 */
public class MayYouLikeFragment extends Fragment {

    private static int mStartIndex;

    public static MayYouLikeFragment newsInstance(int pos) {
        MayYouLikeFragment fragment = new MayYouLikeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        mStartIndex = pos;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        TextView view = new TextView(MyUtils.getContext());
        if (mStartIndex == MyConstants.MOVIE_MAY_YOU_LIKE_INDEX) {
            view.setText("电影：你可能喜欢");
        } else if (mStartIndex == MyConstants.BOOK_MAY_YOU_LIKE_BOOK_INDEX) {
            view.setText("书籍：你可能喜欢");
        } else if (mStartIndex == MyConstants.MUSIC_MAY_YOU_LIKE_MUSIC_INDEX) {
            view.setText("音乐：你可能喜欢");
        } else {

        }
        view.setTextColor(Color.GREEN);
        view.setTextSize(35);
        return view;
    }
}
