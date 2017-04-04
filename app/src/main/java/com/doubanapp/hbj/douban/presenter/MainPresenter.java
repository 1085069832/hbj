package com.doubanapp.hbj.douban.presenter;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.doubanapp.hbj.douban.IPresenter.IMainPresenter;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.IView.IMainView;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.adapter.MyContentViewPagerAdapter;
import com.doubanapp.hbj.douban.utils.MyUtils;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class MainPresenter implements IMainPresenter {

    private IMainView iMainView;
    private MainActivity mContext;

    public MainPresenter(MainActivity mContext, IMainView iMainView) {
        this.iMainView = iMainView;
        this.mContext = mContext;
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
    public void doInitAdapter() {
        MyContentViewPagerAdapter adapter = new MyContentViewPagerAdapter(mContext.getSupportFragmentManager());
        iMainView.onInitAdapter(adapter);
    }

}
