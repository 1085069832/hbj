package com.doubanapp.hbj.douban.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class BaseActivity extends SwipeBackActivity {

    private static final String TAG = "BaseActivity";

    /*
    开启新activity的动画
   */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_open_enter_anim, 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //设置滑动关闭
        SwipeBackLayout swipeBackLayout = getSwipeBackLayout();
        swipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setNavigationBarTintEnabled(true);
            systemBarTintManager.setStatusBarTintColor(getResources().getColor(R.color.colorStatus));
            systemBarTintManager.setNavigationBarTintColor(getResources().getColor(R.color.colorNavigation));
        }
    }

    /*
      关闭activity的动画
        */
    @Override
    public void finish() {
        super.finish();
        if (!(this instanceof MainActivity)) {
            //不是主页,设置动画
            overridePendingTransition(0, R.anim.activity_close_exit_anim);
        }
    }
}
