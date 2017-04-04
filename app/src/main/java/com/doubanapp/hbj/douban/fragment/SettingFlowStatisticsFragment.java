package com.doubanapp.hbj.douban.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.utils.MyUtils;

/**流量统计
 * Created by Administrator on 2017/3/24 0024.
 */
public class SettingFlowStatisticsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(MyUtils.getContext());
        view.setTextSize(35);
        view.setText("流量统计");
        return view;
    }
}
