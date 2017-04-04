package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.utils.MyUtils;

/**
 * 新书速递
 * Created by Administrator on 2017/3/27 0027.
 */
public class BookNewBookFragment extends Fragment {

    public static BookNewBookFragment newsInstance(int pos) {
        BookNewBookFragment fragment = new BookNewBookFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(MyUtils.getContext());
        view.setText("新书速递");
        view.setTextColor(Color.GREEN);
        view.setTextSize(35);
        return view;
    }
}
