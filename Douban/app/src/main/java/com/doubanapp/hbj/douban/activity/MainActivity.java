package com.doubanapp.hbj.douban.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.adapter.MyContentViewPagerAdapter;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 主界面
* */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private NoScrollViewPager vp_content;
    private RadioGroup rg_content;
    private int currentChecked = 0;//标记当前选中的radio
    private static Map<String, String> mIsCheckedMap = new HashMap<>();//guid选中的标签
    private List<String> permissionList = new ArrayList<>();
    private Toolbar toolbar;
    private int navigationIndex = -1;
    private DrawerLayout drawer;

    public static void startAction(Context context, Map<String, String> isCheckedMap) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        mIsCheckedMap = isCheckedMap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        vp_content = (NoScrollViewPager) findViewById(R.id.vp_content);
        rg_content = (RadioGroup) findViewById(R.id.rg_content);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initAnimation();

        initData();

        initEvent();

        //申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] strings = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, strings, 1);
        }
    }

    private void initAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.window_main_enter_anim);
        drawer.startAnimation(animation);
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

    private void initData() {
/*        if (mIsCheckedMap != null) {
            String guidMovieItemKey = mIsCheckedMap.get(GuidActivity.guidMovieItemKey[0]);
            String guidBookItemKey = mIsCheckedMap.get(GuidActivity.guidBookItemKey[1]);
            String guidMusicItemKey = mIsCheckedMap.get(GuidActivity.guidMusicItemKey[2]);
            MyLogUtils.i(TAG, guidMovieItemKey + " " + guidBookItemKey + " " + guidMusicItemKey);
        }*/
        MyContentViewPagerAdapter adapter = new MyContentViewPagerAdapter(getSupportFragmentManager());
        vp_content.setAdapter(adapter);
    }

    private void initEvent() {
        rg_content.check(R.id.rb_home);
        rg_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        currentChecked = 0;
                        toolbar.setTitle("首页");
                        break;
                    case R.id.rb_movie:
                        currentChecked = 1;
                        toolbar.setTitle("电影");
                        break;
                    case R.id.rb_book:
                        currentChecked = 2;
                        toolbar.setTitle("书籍");
                        break;
                    case R.id.rb_music:
                        currentChecked = 3;
                        toolbar.setTitle("音乐");
                        break;
                    default:
                }
                vp_content.setCurrentItem(currentChecked);
            }
        });

        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (navigationIndex != -1) {
                    //开启MenuActivity
                    MenuActivity.startAction(MainActivity.this, navigationIndex);
                    navigationIndex = -1;
                }
            }
        });
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

}
