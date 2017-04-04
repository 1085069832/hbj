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

/**榜单精选 日韩
 * Created by Administrator on 2017/3/26 0026.
 */
public class MovieListSelectionJKFragment extends Fragment {

    public static MovieListSelectionJKFragment newsInstance(int pos) {
        MovieListSelectionJKFragment fragment = new MovieListSelectionJKFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(MyUtils.getContext());
        view.setText("日韩");
        view.setTextColor(Color.GREEN);
        view.setTextSize(35);
        return view;
    }
}
