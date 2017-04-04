package com.doubanapp.hbj.douban.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MenuActivity;
import com.doubanapp.hbj.douban.activity.SettingItemActivity;

/**
 * Created by Administrator on 2017/3/20 0020.
 */
public class MenuSettingFragment extends Fragment implements View.OnClickListener {

    private int clickSettingPos = 0;
    private MenuActivity mContext;

    @Override
    public void onAttach(Context context) {
        mContext = (MenuActivity) getContext();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_menu_setting, container, false);
        Button bt_me_changelanguage = (Button) view.findViewById(R.id.bt_me_changelanguage);
        Button bt_me_changefontsize = (Button) view.findViewById(R.id.bt_me_changefontsize);
        Button bt_me_myliketag = (Button) view.findViewById(R.id.bt_me_myliketag);
        Button bt_me_checknet = (Button) view.findViewById(R.id.bt_me_checknet);
        Button bt_me_flow = (Button) view.findViewById(R.id.bt_me_flow);
        Button bt_me_clear = (Button) view.findViewById(R.id.bt_me_clear);
        Button bt_me_about = (Button) view.findViewById(R.id.bt_me_about);

        bt_me_changelanguage.setOnClickListener(this);
        bt_me_changefontsize.setOnClickListener(this);
        bt_me_myliketag.setOnClickListener(this);
        bt_me_checknet.setOnClickListener(this);
        bt_me_flow.setOnClickListener(this);
        bt_me_clear.setOnClickListener(this);
        bt_me_about.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_me_changelanguage:
                clickSettingPos = 0;
                break;
            case R.id.bt_me_changefontsize:
                clickSettingPos = 1;
                break;
            case R.id.bt_me_myliketag:
                clickSettingPos = 2;
                break;
            case R.id.bt_me_checknet:
                clickSettingPos = 3;
                break;
            case R.id.bt_me_flow:
                clickSettingPos = 4;
                break;
            case R.id.bt_me_clear:
                clickSettingPos = 5;
                break;
            case R.id.bt_me_about:
                clickSettingPos = 6;
                break;
            default:
        }

        if (clickSettingPos == 5) {
            //清理缓存

        } else {
            SettingItemActivity.startAction(mContext, clickSettingPos);
        }
    }
}
