package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.utils.MyUtils;

/**兴趣标签
 * Created by Administrator on 2017/3/24 0024.
 */
public class SettingLikeTagFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(MyUtils.getContext());
        view.setTextSize(35);
        view.setText("兴趣标签");
        return view;
    }
}
