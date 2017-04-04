package com.doubanapp.hbj.douban.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.doubanapp.hbj.douban.activity.MainActivity;

/**懒加载
 * Created by Administrator on 2017/3/17 0017.
 */
public abstract class LazyFragment extends Fragment {

    protected boolean isVisible;//标记是否显示
    protected MainActivity mContext;

    @Override
    public void onAttach(Context context) {
        mContext = (MainActivity) getActivity();
        super.onAttach(context);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isVisible = true;
            //显示
            onVisible();
        } else {
            //隐藏
            isVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

}
