package com.doubanapp.hbj.douban.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.doubanapp.hbj.douban.IView.IMainView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.MyContentViewPagerAdapter;
import com.doubanapp.hbj.douban.presenter.MainPresenter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
* 主界面
* */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener, View.OnClickListener, IMainView {

    private static final String TAG = "MainActivity";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    private int currentChecked = 0;//标记当前选中的radio
    private static Map<String, String> mIsCheckedMap = new HashMap<>();//guid选中的标签
    private List<String> permissionList = new ArrayList<>();
    private int navigationIndex = -1;

    public static void startAction(Context context, Map<String, String> isCheckedMap) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        mIsCheckedMap = isCheckedMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //申请权限
        checkPermission();

        //设置不能滑动退出
        setSwipeBackEnable(false);

        //set listener
        drawer.addDrawerListener(this);
        navView.setNavigationItemSelectedListener(this);
        fab.setOnClickListener(this);

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("首页", ContextCompat.getColor(this, R.color.colorPrimary), R.mipmap.ic_home_bottom_navigation);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("电影", ContextCompat.getColor(this, R.color.default_line_indicator_selected_color), R.mipmap.ic_movie_bottom_navigation);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("书籍", ContextCompat.getColor(this, R.color.vpi__bright_foreground_disabled_holo_dark), R.mipmap.ic_book_bottom_navigation);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("音乐", ContextCompat.getColor(this, R.color.colorAccent), R.mipmap.ic_music_bottom_navigation);

        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);

        //Presenter
        MainPresenter mainPresenter = new MainPresenter(this, this);
        mainPresenter.doAnim();
        mainPresenter.doInitToolBar();
        mainPresenter.doGuidMapCheckResult(mIsCheckedMap);
        mainPresenter.doInitAdapter();
    }

    /*
    * 申请权限*/
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] toArray = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, toArray, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(MainActivity.this, "必须同意权限才能使用", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        if (id == R.id.action_search) {
            MyLogUtils.i("action_search", "搜索");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_looked) {
            navigationIndex = 0;
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_collect) {
            navigationIndex = 1;
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_mystyle) {
            navigationIndex = 2;
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_setting) {
            navigationIndex = 3;
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_share) {
            navigationIndex = 4;
        } else if (id == R.id.nav_night) {
            navigationIndex = 5;
        }

        return true;
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
    }

    @Override
    public void onDrawerOpened(View drawerView) {
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        if (navigationIndex != -1) {
            //开启MenuActivity
            MenuActivity.startAction(MainActivity.this, navigationIndex);
            navigationIndex = -1;
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    /*
    * 动画*/
    @Override
    public void onInitAnim(Animation animation) {
        drawer.startAnimation(animation);
    }

    /*
    * 设置toolbar*/
    @Override
    public void onInitToolBar() {
        toolbar.setTitle("首页");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    /*
    * guidcheck的选中的结果*/
    @Override
    public void onGuidMapCheckResult(Map<String, String> mIsCheckedMap) {

    }

    /*
    * adapter*/
    @Override
    public void onInitAdapter(MyContentViewPagerAdapter adapter) {
    }
}
