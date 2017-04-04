package com.doubanapp.hbj.douban.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.utils.MyLogUtils;

import java.util.HashMap;
import java.util.Map;

/*
* 设置向导界面
* */
public class GuidActivity extends AppCompatActivity implements CheckBox.OnCheckedChangeListener, View.OnClickListener {

    private static final String TAG = "GuidActivity";
    private LinearLayout ll_guid_movie;
    private LinearLayout ll_guid_book;
    private LinearLayout ll_guid_music;
    private float width;
    private TextView tv_guid_start_home;
    private Map<String, String> isCheckedMap = new HashMap<>();//保存选中的状�?
    public static String[] guidMovieItemKey = {"cb_guid_movie_item_jingdian", "cb_guid_movie_item_doubangaofen", "cb_guid_movie_item_lengmengjiapian", "cb_guid_movie_item_juqing", "cb_guid_movie_item_aiqing",
            "cb_guid_movie_item_xiju", "cb_guid_movie_item_kehuan", "cb_guid_movie_item_wengyi", "cb_guid_movie_item_dongzuo", "cb_guid_movie_item_xuanyi", "cb_guid_movie_item_qingchun"};

    public static String[] guidBookItemKey = {"cb_guid_book_item_xiaoshuo", "cb_guid_book_item_qihuan", "cb_guid_book_item_waiguowenzi", "cb_guid_book_item_qingchun", "cb_guid_book_item_lizhi", "cb_guid_book_item_suibi", "cb_guid_book_item_aiqing"};

    public static String[] guidMusicItemKey = {"cb_guid_music_item_liuxing", "cb_guid_music_item_yaogun", "cb_guid_music_item_minyao", "cb_guid_music_item_duli", "cb_guid_music_item_huayu", "cb_guid_music_item_oumei", "cb_guid_music_item_riben", "cb_guid_music_item_hanguo"};

    public static String[] guidMovieItemValue = {"经典", "豆瓣高分", "冷门佳片", "剧情", "爱情",
            "喜剧", "科幻", "文艺", "动作", "悬疑", "青春"};
    public static String[] guidBookItemValue = {"小说", "奇幻", "外国文字", "青春", "励志", "随笔", "爱情"};
    public static String[] guidMusicItemValue = {"流行", "摇滚", "民谣", "独立", "华语", "欧美", "日本", "韩国"};

    private int checkedCount = 0;//记录选中的个�?
    private ImageView iv_guid_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        initAnimation();
    }

    private void initView() {
        setContentView(R.layout.activity_guid);
        ll_guid_movie = (LinearLayout) findViewById(R.id.ll_guid_movie);
        ll_guid_book = (LinearLayout) findViewById(R.id.ll_guid_book);
        ll_guid_music = (LinearLayout) findViewById(R.id.ll_guid_music);
        tv_guid_start_home = (TextView) findViewById(R.id.tv_guid_start_home);
        iv_guid_cancel = (ImageView) findViewById(R.id.iv_guid_cancel);
	iv_guid_cancel .setOnClickListener(this);
	tv_guid_start_home.setOnClickListener(this);

        //电影
        LinearLayout ll_guid_movie_item_1 = (LinearLayout) findViewById(R.id.ll_guid_movie_item_1);
        LinearLayout ll_guid_movie_item_2 = (LinearLayout) findViewById(R.id.ll_guid_movie_item_2);
        LinearLayout ll_guid_movie_item_3 = (LinearLayout) findViewById(R.id.ll_guid_movie_item_3);
        //书籍
        LinearLayout ll_guid_book_item_1 = (LinearLayout) findViewById(R.id.ll_guid_book_item_1);
        LinearLayout ll_guid_book_item_2 = (LinearLayout) findViewById(R.id.ll_guid_book_item_2);
        //音乐
        LinearLayout ll_guid_music_item_1 = (LinearLayout) findViewById(R.id.ll_guid_music_item_1);
        LinearLayout ll_guid_music_item_2 = (LinearLayout) findViewById(R.id.ll_guid_music_item_2);

        //设置监听
        //电影
        for (int i = 0; i < ll_guid_movie_item_1.getChildCount(); i++) {
            ((CheckBox) ll_guid_movie_item_1.getChildAt(i)).setOnCheckedChangeListener(this);
        }
        for (int i = 0; i < ll_guid_movie_item_2.getChildCount(); i++) {
            ((CheckBox) ll_guid_movie_item_2.getChildAt(i)).setOnCheckedChangeListener(this);
        }
        for (int i = 0; i < ll_guid_movie_item_3.getChildCount(); i++) {
            ((CheckBox) ll_guid_movie_item_3.getChildAt(i)).setOnCheckedChangeListener(this);
        }
        //书籍
        for (int i = 0; i < ll_guid_book_item_1.getChildCount(); i++) {
            ((CheckBox) ll_guid_book_item_1.getChildAt(i)).setOnCheckedChangeListener(this);
        }
        for (int i = 0; i < ll_guid_book_item_2.getChildCount(); i++) {
            ((CheckBox) ll_guid_book_item_2.getChildAt(i)).setOnCheckedChangeListener(this);
        }
        //音乐
        for (int i = 0; i < ll_guid_music_item_1.getChildCount(); i++) {
            ((CheckBox) ll_guid_music_item_1.getChildAt(i)).setOnCheckedChangeListener(this);
        }
        for (int i = 0; i < ll_guid_music_item_2.getChildCount(); i++) {
            ((CheckBox) ll_guid_music_item_2.getChildAt(i)).setOnCheckedChangeListener(this);
        }

        width = getWindowManager().getDefaultDisplay().getWidth();//屏幕宽度
        MyLogUtils.i(TAG, width + "");
    }

    private void initAnimation() {
        TranslateAnimation forwardTranslateAnimation = new TranslateAnimation(-width, 0f, 0, 0f);
        TranslateAnimation reverseTranslateAnimation = new TranslateAnimation(width, 0f, 0, 0f);
        forwardTranslateAnimation.setDuration(1000);
        reverseTranslateAnimation.setDuration(1000);
        ll_guid_movie.startAnimation(forwardTranslateAnimation);
        ll_guid_book.startAnimation(reverseTranslateAnimation);
        ll_guid_music.startAnimation(forwardTranslateAnimation);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_guid_movie_item_jingdian:
                savaChecked(isChecked, guidMovieItemKey[0], guidMovieItemValue[0]);
                MyLogUtils.i("经典", "选中了经�?);
                break;
            case R.id.cb_guid_movie_item_doubangaofen:
                savaChecked(isChecked, guidMovieItemKey[1], guidMovieItemValue[1]);
                break;
            case R.id.cb_guid_movie_item_lengmengjiapian:
                savaChecked(isChecked, guidMovieItemKey[2], guidMovieItemValue[2]);
                break;
            case R.id.cb_guid_movie_item_juqing:
                savaChecked(isChecked, guidMovieItemKey[3], guidMovieItemValue[3]);
                break;
            case R.id.cb_guid_movie_item_aiqing:
                savaChecked(isChecked, guidMovieItemKey[4], guidMovieItemValue[4]);
                break;
            case R.id.cb_guid_movie_item_xiju:
                savaChecked(isChecked, guidMovieItemKey[5], guidMovieItemValue[5]);
                break;
            case R.id.cb_guid_movie_item_kehuan:
                savaChecked(isChecked, guidMovieItemKey[6], guidMovieItemValue[6]);
                break;
            case R.id.cb_guid_movie_item_wengyi:
                savaChecked(isChecked, guidMovieItemKey[7], guidMovieItemValue[7]);
                break;
            case R.id.cb_guid_movie_item_dongzuo:
                savaChecked(isChecked, guidMovieItemKey[8], guidMovieItemValue[8]);
                break;
            case R.id.cb_guid_movie_item_xuanyi:
                savaChecked(isChecked, guidMovieItemKey[9], guidMovieItemValue[9]);
                break;
            case R.id.cb_guid_movie_item_qingchun:
                savaChecked(isChecked, guidMovieItemKey[10], guidMovieItemValue[10]);
                break;
            case R.id.cb_guid_book_item_xiaoshuo:
                savaChecked(isChecked, guidBookItemKey[0], guidBookItemValue[0]);
                break;
            case R.id.cb_guid_book_item_qihuan:
                savaChecked(isChecked, guidBookItemKey[1], guidBookItemValue[1]);
                break;
            case R.id.cb_guid_book_item_waiguowenzi:
                savaChecked(isChecked, guidBookItemKey[2], guidBookItemValue[2]);
                break;
            case R.id.cb_guid_book_item_qingchun:
                savaChecked(isChecked, guidBookItemKey[3], guidBookItemValue[3]);
                break;
            case R.id.cb_guid_book_item_lizhi:
                savaChecked(isChecked, guidBookItemKey[4], guidBookItemValue[4]);
                break;
            case R.id.cb_guid_book_item_suibi:
                savaChecked(isChecked, guidBookItemKey[5], guidBookItemValue[5]);
                break;
            case R.id.cb_guid_book_item_aiqing:
                savaChecked(isChecked, guidBookItemKey[6], guidBookItemValue[6]);
                break;
            case R.id.cb_guid_music_item_liuxing:
                savaChecked(isChecked, guidMusicItemKey[0], guidMusicItemValue[0]);
                break;
            case R.id.cb_guid_music_item_yaogun:
                savaChecked(isChecked, guidMusicItemKey[1], guidMusicItemValue[1]);
                break;
            case R.id.cb_guid_music_item_minyao:
                savaChecked(isChecked, guidMusicItemKey[2], guidMusicItemValue[2]);
                break;
            case R.id.cb_guid_music_item_duli:
                savaChecked(isChecked, guidMusicItemKey[3], guidMusicItemValue[3]);
                break;
            case R.id.cb_guid_music_item_huayu:
                savaChecked(isChecked, guidMusicItemKey[4], guidMusicItemValue[4]);
                break;
            case R.id.cb_guid_music_item_oumei:
                savaChecked(isChecked, guidMusicItemKey[5], guidMusicItemValue[5]);
                break;
            case R.id.cb_guid_music_item_riben:
                savaChecked(isChecked, guidMusicItemKey[6], guidMusicItemValue[6]);
                break;
            case R.id.cb_guid_music_item_hanguo:
                savaChecked(isChecked, guidMusicItemKey[7], guidMusicItemValue[7]);
                break;
            default:
        }
        //改变进入主界面按钮状�?
        changeStartHomeState();

        MyLogUtils.i("checkedCount", checkedCount + "");
        MyLogUtils.i("isCheckedMap", isCheckedMap.size() + "");
    }

    /*
    * 开启主界面按钮状�?/
    private void changeStartHomeState() {
        if (checkedCount >= 1) {
            tv_guid_start_home.setTextColor(Color.parseColor("#009900"));
            tv_guid_start_home.setEnabled(true);
        } else {
            tv_guid_start_home.setTextColor(Color.parseColor("#999999"));
            tv_guid_start_home.setEnabled(false);
        }
    }

    /*
    * 保存选中的状�?
    * */
    private void savaChecked(boolean isChecked, String key, String value) {
        if (isChecked) {
            checkedCount++;
            isCheckedMap.put(key, value);
        } else {
            checkedCount--;
            isCheckedMap.put(key, null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_guid_cancel:
                MainActivity.startAction(GuidActivity.this, null);
                finish();
                break;
            case R.id.tv_guid_start_home:
                MainActivity.startAction(GuidActivity.this, isCheckedMap);
                finish();
                break;
            default:
        }
    }
}
