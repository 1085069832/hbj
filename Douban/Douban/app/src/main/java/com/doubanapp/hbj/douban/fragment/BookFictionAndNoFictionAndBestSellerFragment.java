package com.doubanapp.hbj.douban.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.utils.MyUtils;

/**
 * 虚构和非虚构图书
 * Created by Administrator on 2017/3/27 0027.
 */
public class BookFictionAndNoFictionAndBestSellerFragment extends Fragment {

    private static int mStartPos;

    public static BookFictionAndNoFictionAndBestSellerFragment newsInstance(int pos) {
        BookFictionAndNoFictionAndBestSellerFragment fragment = new BookFictionAndNoFictionAndBestSellerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        fragment.setArguments(bundle);
        mStartPos = pos;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = new TextView(MyUtils.getContext());
        if (mStartPos == MyConstants.BOOK_FICTION_BOOK_INDEX) {
            view.setText("虚构类图书");
        } else if (mStartPos == MyConstants.BOOK_NOFICTION_BOOK_INDEX) {
            view.setText("非虚构类图书");
        } else {
            view.setText("畅销书籍");
        }
        view.setTextColor(Color.GREEN);
        view.setTextSize(35);
        return view;
    }
}
