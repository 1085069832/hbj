package com.doubanapp.hbj.douban.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.fragment.BookFictionAndNoFictionAndBestSellerFragment;
import com.doubanapp.hbj.douban.fragment.BookNewBookFragment;
import com.doubanapp.hbj.douban.fragment.MayYouLikeFragment;
import com.doubanapp.hbj.douban.fragment.MovieHotShowAndComingSoonFragment;
import com.doubanapp.hbj.douban.fragment.MovieListSelectionEAFragment;
import com.doubanapp.hbj.douban.fragment.MovieListSelectionJKFragment;
import com.doubanapp.hbj.douban.fragment.MovieListSelectionNewMovieFragment;
import com.doubanapp.hbj.douban.fragment.MovieListSelectionTop250Fragment;
import com.doubanapp.hbj.douban.fragment.MusicNewAndHuaYuAndOuMeiAndRiHanFragment;
import com.doubanapp.hbj.douban.fragment.SelectFragment;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.view.MyToolBar;

/*
* 各个界面条目
* */
public class TopicActivity extends BaseActivity implements MyToolBar.onMyNavigationOnClickListener {

    public static final String TAG = "TopicActivity";
    private static int mStartPos;
    private static String mSelectTag;//选中的电影标签
    private MyToolBar tb_movie_hot_topic;

    public static void startAction(Context context, int startPos) {
        Intent intent = new Intent(context, TopicActivity.class);
        context.startActivity(intent);
        mStartPos = startPos;
        MyLogUtils.i(TAG, mStartPos + "");
    }

    public static void startAction(Context context, int startPos, String selectIndex) {
        Intent intent = new Intent(context, TopicActivity.class);
        context.startActivity(intent);
        mStartPos = startPos;
        mSelectTag = selectIndex;
        MyLogUtils.i(TAG + selectIndex, mSelectTag + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        replaceView();
    }

    private void initView() {
        setContentView(R.layout.activity_movie_topic);
        tb_movie_hot_topic = (MyToolBar) findViewById(R.id.tb_movie_hot_topic);
        tb_movie_hot_topic.setOnMyNavigationOnClickListener(this);
    }

    private void replaceView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (mStartPos) {
            //电影
            case MyConstants.MOVIE_HOT_SHOW_INDEX://正在热映
                tb_movie_hot_topic.setTitle("院线电影");
                MovieHotShowAndComingSoonFragment movieHotShowFragment = MovieHotShowAndComingSoonFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieHotShowFragment);
                break;
            case MyConstants.MOVIE_COMING_SOON_INDEX://即将上映
                tb_movie_hot_topic.setTitle("院线电影");
                MovieHotShowAndComingSoonFragment movieComingSoonFragment = MovieHotShowAndComingSoonFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieComingSoonFragment);
                break;
            case MyConstants.MOVIE_LIST_SELECTION_TOP250_INDEX://TOP250
                tb_movie_hot_topic.setTitle("豆瓣 Top250");
                MovieListSelectionTop250Fragment movieListSelectionTop250Fragment = MovieListSelectionTop250Fragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieListSelectionTop250Fragment);
                break;
            case MyConstants.MOVIE_LIST_SELECTION_NEWMOVIE_INDEX://新片
                tb_movie_hot_topic.setTitle("豆瓣 新片");
                MovieListSelectionNewMovieFragment movieListSelectionNewMovieFragment = MovieListSelectionNewMovieFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieListSelectionNewMovieFragment);
                break;
            case MyConstants.MOVIE_LIST_SELECTION_EA_INDEX://欧美
                tb_movie_hot_topic.setTitle("豆瓣 欧美");
                MovieListSelectionEAFragment movieListSelectionEAFragment = MovieListSelectionEAFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieListSelectionEAFragment);
                break;
            case MyConstants.MOVIE_LIST_SELECTION_JK_INDEX://日韩
                tb_movie_hot_topic.setTitle("豆瓣 日韩");
                MovieListSelectionJKFragment movieListSelectionJKFragment = MovieListSelectionJKFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieListSelectionJKFragment);
                break;
            case MyConstants.MOVIE_MAY_YOU_LIKE_INDEX://你可能感兴趣
                tb_movie_hot_topic.setTitle("你可能感兴趣");
                MayYouLikeFragment movieMayYouLikeFragment = MayYouLikeFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieMayYouLikeFragment);
                break;
            case MyConstants.MOVIE_SELECT_MOVIE_INDEX://选电影
                tb_movie_hot_topic.setTitle("选电影");
                SelectFragment movieSelectMovieFragment = SelectFragment.newsInstance(mStartPos, mSelectTag);
                fragmentTransaction.replace(R.id.fl_movie_topic, movieSelectMovieFragment);
                break;
            //书籍
            case MyConstants.BOOK_NEW_BOOK_INDEX://新书速递
                tb_movie_hot_topic.setTitle("新书速递");
                BookNewBookFragment bookNewBookFragment = BookNewBookFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, bookNewBookFragment);
                break;
            case MyConstants.BOOK_FICTION_BOOK_INDEX://虚构类图书
                tb_movie_hot_topic.setTitle("虚构类图书");
                BookFictionAndNoFictionAndBestSellerFragment bookFictionFragment = BookFictionAndNoFictionAndBestSellerFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, bookFictionFragment);
                break;
            case MyConstants.BOOK_NOFICTION_BOOK_INDEX://非虚构类图书
                tb_movie_hot_topic.setTitle("非虚构类图书");
                BookFictionAndNoFictionAndBestSellerFragment bookNoFictionFragment = BookFictionAndNoFictionAndBestSellerFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, bookNoFictionFragment);
                break;
            case MyConstants.BOOK_BESTSELLER_BOOK_INDEX://畅销书籍
                tb_movie_hot_topic.setTitle("畅销书籍");
                BookFictionAndNoFictionAndBestSellerFragment bestSellerFragment = BookFictionAndNoFictionAndBestSellerFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, bestSellerFragment);
                break;
            case MyConstants.BOOK_MAY_YOU_LIKE_BOOK_INDEX://你可能感兴趣
                tb_movie_hot_topic.setTitle("你可能感兴趣");
                MayYouLikeFragment bookMayYouLikeFragment = MayYouLikeFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, bookMayYouLikeFragment);
                break;
            case MyConstants.BOOK_SELECT_BOOK_INDEX://选图书
                tb_movie_hot_topic.setTitle("选图书");
                SelectFragment bookSelectMovieFragment = SelectFragment.newsInstance(mStartPos, mSelectTag);
                fragmentTransaction.replace(R.id.fl_movie_topic, bookSelectMovieFragment);
                break;
            //歌曲
            case MyConstants.MUSIC_NEW_MUSIC_INDEX://新曲
                tb_movie_hot_topic.setTitle("新曲");
                MusicNewAndHuaYuAndOuMeiAndRiHanFragment musicNewFragment = MusicNewAndHuaYuAndOuMeiAndRiHanFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, musicNewFragment);
                break;
            case MyConstants.MUSIC_HUAYU_MUSIC_INDEX://华语音乐
                tb_movie_hot_topic.setTitle("华语音乐");
                MusicNewAndHuaYuAndOuMeiAndRiHanFragment musicHuaYuFragment = MusicNewAndHuaYuAndOuMeiAndRiHanFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, musicHuaYuFragment);
                break;
            case MyConstants.MUSIC_OUMEI_MUSIC_INDEX://欧美音乐
                tb_movie_hot_topic.setTitle("欧美音乐");
                MusicNewAndHuaYuAndOuMeiAndRiHanFragment musicOuMeiFragment = MusicNewAndHuaYuAndOuMeiAndRiHanFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, musicOuMeiFragment);
                break;
            case MyConstants.MUSIC_RIHAN_MUSIC_INDEX://日韩音乐
                tb_movie_hot_topic.setTitle("日韩音乐");
                MusicNewAndHuaYuAndOuMeiAndRiHanFragment musicRiHanFragment = MusicNewAndHuaYuAndOuMeiAndRiHanFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, musicRiHanFragment);
                break;
            case MyConstants.MUSIC_MAY_YOU_LIKE_MUSIC_INDEX://你可能感兴趣
                tb_movie_hot_topic.setTitle("你可能感兴趣");
                MayYouLikeFragment musicMayYouLikeFragment = MayYouLikeFragment.newsInstance(mStartPos);
                fragmentTransaction.replace(R.id.fl_movie_topic, musicMayYouLikeFragment);
                break;
            case MyConstants.MUSIC_SELECT_MUSIC_INDEX://选音乐
                tb_movie_hot_topic.setTitle("选音乐");
                SelectFragment musicSelectMovieFragment = SelectFragment.newsInstance(mStartPos, mSelectTag);
                fragmentTransaction.replace(R.id.fl_movie_topic, musicSelectMovieFragment);
                break;
            default:
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onMyToolbarClick(View v) {
        finish();
    }
}
