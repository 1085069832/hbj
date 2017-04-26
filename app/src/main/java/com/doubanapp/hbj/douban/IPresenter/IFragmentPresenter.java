package com.doubanapp.hbj.douban.IPresenter;

import android.support.v7.widget.RecyclerView;

import com.flipboard.bottomsheet.BottomSheetLayout;

/**
 * Created by Administrator
 * time: 2017-04-04.
 */

public interface IFragmentPresenter {

    void doInitBottomSheet(BottomSheetLayout bottomSheet);

    void doRegisterMultitypeItem(RecyclerView rc);

    void doInitLinearLayoutManager();

    void doInitStaggeredGridLayoutManager();

    void doConnectHttp(int selectPage, boolean isLoadMore);

    void doDestroy();
}
