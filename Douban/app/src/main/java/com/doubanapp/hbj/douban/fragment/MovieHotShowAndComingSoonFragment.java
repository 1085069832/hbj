package com.doubanapp.hbj.douban.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.TopicActivity;
import com.doubanapp.hbj.douban.adapter.MovieHotShowAndComingSoonViewPagerAdapter;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.viewpagerindicator.TabPageIndicator;

/**
 * 电影正在热映和即将上映
 * Created by Administrator on 2017/3/26 0026.
 */
public class MovieHotShowAndComingSoonFragment extends Fragment {

    public static final String TAG = "MovieHotShowAndComingSoonFragment";
    private ViewPager vp_movie_hotshow_comingsoon_topic;
    private TabPageIndicator ti_movie_hotshow_comingsoon_topic;
    private TopicActivity mContext;
    private static int mPos;

    public static MovieHotShowAndComingSoonFragment newsInstance(int pos) {
        MovieHotShowAndComingSoonFragment fragment = new MovieHotShowAndComingSoonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        mPos = pos;
        MyLogUtils.i(TAG, mPos + "");
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        mContext = (TopicActivity) getContext();
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_movie_hotshow_comingsoon, container, false);
        ti_movie_hotshow_comingsoon_topic = (TabPageIndicator) view.findViewById(R.id.ti_movie_hotshow_comingsoon_topic);
        vp_movie_hotshow_comingsoon_topic = (ViewPager) view.findViewById(R.id.vp_movie_hotshow_comingsoon_topic);
        MovieHotShowAndComingSoonViewPagerAdapter adapter = new MovieHotShowAndComingSoonViewPagerAdapter(mContext.getSupportFragmentManager());
        vp_movie_hotshow_comingsoon_topic.setAdapter(adapter);
        ti_movie_hotshow_comingsoon_topic.setViewPager(vp_movie_hotshow_comingsoon_topic);

        if (mPos == MyConstants.MOVIE_COMING_SOON_INDEX) {
            vp_movie_hotshow_comingsoon_topic.setCurrentItem(1);
        }

        return view;
    }
}
