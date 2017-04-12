package com.doubanapp.hbj.douban.presenter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.doubanapp.hbj.douban.IPresenter.IMainPresenter;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.IView.IMainView;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.adapter.MyContentViewPagerAdapter;
import com.doubanapp.hbj.douban.fragment.BookFragment;
import com.doubanapp.hbj.douban.fragment.HomeFragment;
import com.doubanapp.hbj.douban.fragment.MovieFragment;
import com.doubanapp.hbj.douban.fragment.MusicFragment;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class MainPresenter implements IMainPresenter {

    private IMainView iMainView;
    private MainActivity mContext;
    private List<BottomNavigationItem> bottomNavigationItemsList;
    private HomeFragment homeFragment;
    private MovieFragment movieFragment;
    private BookFragment bookFragment;
    private MusicFragment musicFragment;
    private FragmentManager supportFragmentManager;
    private FragmentTransaction fragmentTransaction;
    private int[] color = {R.color.colorPrimary, R.color.default_line_indicator_selected_color,
            R.color.vpi__bright_foreground_disabled_holo_dark, R.color.colorAccent};

    public MainPresenter(MainActivity mContext, IMainView iMainView) {
        this.iMainView = iMainView;
        this.mContext = mContext;
        supportFragmentManager = mContext.getSupportFragmentManager();
    }

    @Override
    public void doAnim() {
        Animation animation = AnimationUtils.loadAnimation(MyUtils.getContext(), R.anim.activity_main_enter_anim);
        iMainView.onInitAnim(animation);
    }

    @Override
    public void doInitToolBar() {
        iMainView.onInitToolBar();
    }

    @Override
    public void doGuidMapCheckResult(Map<String, String> mIsCheckedMap) {
        //处理选中的tag
        /*if (mIsCheckedMap != null) {
            String guidMovieItemKey = mIsCheckedMap.get(GuidActivity.guidMovieItemKey[0]);
            String guidBookItemKey = mIsCheckedMap.get(GuidActivity.guidBookItemKey[1]);
            String guidMusicItemKey = mIsCheckedMap.get(GuidActivity.guidMusicItemKey[2]);
        }*/
        // iMainView.onGuidMapCheckResult();
    }

    @Override
    public void doInitNavigationBottom() {
        bottomNavigationItemsList = new ArrayList<>();
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("首页", ContextCompat.getColor(mContext, color[0]), R.mipmap.ic_home_bottom_navigation);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("电影", ContextCompat.getColor(mContext, color[1]), R.mipmap.ic_movie_bottom_navigation);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("书籍", ContextCompat.getColor(mContext, color[2]), R.mipmap.ic_book_bottom_navigation);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("音乐", ContextCompat.getColor(mContext, color[3]), R.mipmap.ic_music_bottom_navigation);
        bottomNavigationItemsList.add(bottomNavigationItem);
        bottomNavigationItemsList.add(bottomNavigationItem1);
        bottomNavigationItemsList.add(bottomNavigationItem2);
        bottomNavigationItemsList.add(bottomNavigationItem3);

        iMainView.onInitNavigationBottom(bottomNavigationItemsList);
    }

    @Override
    public void doInitDefaultFragment() {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        homeFragment = HomeFragment.newsInstance(0);
        fragmentTransaction.add(R.id.fl_content, homeFragment);
        fragmentTransaction.commit();
        iMainView.onNavigationDefaultShow(0);
    }

    @Override
    public void doHideFragment() {
        fragmentTransaction = supportFragmentManager.beginTransaction();
        if (homeFragment != null && !homeFragment.isHidden()) {
            fragmentTransaction.hide(homeFragment);
        }
        if (movieFragment != null && !movieFragment.isHidden()) {
            fragmentTransaction.hide(movieFragment);
        }
        if (bookFragment != null && !bookFragment.isHidden()) {
            fragmentTransaction.hide(bookFragment);
        }
        if (musicFragment != null && !musicFragment.isHidden()) {
            fragmentTransaction.hide(musicFragment);
        }
    }

    @Override
    public void doShowFragment(Toolbar toolbar, FloatingActionButton fab, int index) {
        switch (index) {
            case 0:
                toolbar.setTitle("首页");
                toolbar.setBackgroundResource(color[0]);
                fab.setColorNormalResId(color[0]);
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newsInstance(0);
                    fragmentTransaction.add(R.id.fl_content, homeFragment);
                }
                fragmentTransaction.show(homeFragment);
                break;
            case 1:
                toolbar.setTitle("电影");
                toolbar.setBackgroundResource(color[1]);
                fab.setColorNormalResId(color[1]);
                if (movieFragment == null) {
                    movieFragment = MovieFragment.newsInstance(1);
                    fragmentTransaction.add(R.id.fl_content, movieFragment);
                }
                fragmentTransaction.show(movieFragment);
                break;
            case 2:
                toolbar.setTitle("书籍");
                toolbar.setBackgroundResource(color[2]);
                fab.setColorNormalResId(color[2]);
                if (bookFragment == null) {
                    bookFragment = BookFragment.newsInstance(2);
                    fragmentTransaction.add(R.id.fl_content, bookFragment);
                }
                fragmentTransaction.show(bookFragment);
                break;
            case 3:
                toolbar.setTitle("音乐");
                toolbar.setBackgroundResource(color[3]);
                fab.setColorNormalResId(color[3]);
                if (musicFragment == null) {
                    musicFragment = MusicFragment.newsInstance(3);
                    fragmentTransaction.add(R.id.fl_content, musicFragment);
                }
                fragmentTransaction.show(musicFragment);
                break;
            default:
        }
        fragmentTransaction.commit();
    }

}
