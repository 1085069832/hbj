package com.doubanapp.hbj.douban.IPresenter;

import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IFragmentPresenter {

    void doInitSweetSheet(RelativeLayout rl);

    void doRegisterMultitypeItem(RecyclerView rc);

    void doInitLinearLayoutManager();

    void doInitStaggeredGridLayoutManager();

    void doConnectHttp(int selectPage);

    void doDestroy();
}
