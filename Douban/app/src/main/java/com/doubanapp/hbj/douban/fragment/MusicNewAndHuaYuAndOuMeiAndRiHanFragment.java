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
 * 新曲华语欧美日韩
 * Created by Administrator on 2017/3/27 0027.
 */
public class MusicNewAndHuaYuAndOuMeiAndRiHanFragment extends Fragment {

    private static int mStartPos;

    public static MusicNewAndHuaYuAndOuMeiAndRiHanFragment newsInstance(int pos) {
        MusicNewAndHuaYuAndOuMeiAndRiHanFragment fragment = new MusicNewAndHuaYuAndOuMeiAndRiHanFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        mStartPos = pos;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(MyUtils.getContext());
        if (mStartPos == MyConstants.MUSIC_NEW_MUSIC_INDEX) {
            view.setText("新曲");
        } else if (mStartPos == MyConstants.MUSIC_HUAYU_MUSIC_INDEX) {
            view.setText("华语");
        } else if (mStartPos == MyConstants.MUSIC_OUMEI_MUSIC_INDEX) {
            view.setText("欧美");
        } else {
            view.setText("日韩");
        }
        view.setTextColor(Color.GREEN);
        view.setTextSize(35);
        return view;
    }
}
