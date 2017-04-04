package com.doubanapp.hbj.douban.utils;

import android.support.v4.app.Fragment;

import com.doubanapp.hbj.douban.fragment.BookFragment;
import com.doubanapp.hbj.douban.fragment.HomeFragment;
import com.doubanapp.hbj.douban.fragment.MovieFragment;
import com.doubanapp.hbj.douban.fragment.MusicFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class ContentFragmentFactory {
    private static Map<Integer, Fragment> fragments = new HashMap<>();
    private static Fragment fragment;

    public static Fragment createFragment(int pos) {
        fragment = fragments.get(pos);

        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = HomeFragment.newsInstance(pos);
                    break;
                case 1:
                    fragment = MovieFragment.newsInstance(pos);
                    break;
                case 2:
                    fragment = BookFragment.newsInstance(pos);
                    break;
                case 3:
                    fragment = MusicFragment.newsInstance(pos);
                    break;
                default:
            }
            fragments.put(pos, fragment);
        }

        return fragment;
    }
}
