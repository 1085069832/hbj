package com.doubanapp.hbj.douban.IPresenter;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public interface IMainPresenter {

    void doAnim();

    void doInitToolBar();

    void doGuidMapCheckResult(Map<String, String> mIsCheckedMap);

    void doInitAdapter();
}
