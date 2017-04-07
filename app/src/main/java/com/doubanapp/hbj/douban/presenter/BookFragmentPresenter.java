package com.doubanapp.hbj.douban.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.doubanapp.hbj.douban.IModel.IBookModel;
import com.doubanapp.hbj.douban.IModel.IMusicFragmentModel;
import com.doubanapp.hbj.douban.IPresenter.IBookFragmentPresenter;
import com.doubanapp.hbj.douban.IView.IBookFragmentView;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.model.BookFragmentModel;
import com.doubanapp.hbj.douban.mtitem.ContentIconItem;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.mtitem.SelectItem;
import com.doubanapp.hbj.douban.mtprovider.ContentIconProvider;
import com.doubanapp.hbj.douban.mtprovider.MayYouLikeProvider;
import com.doubanapp.hbj.douban.mtprovider.NormalProvider;
import com.doubanapp.hbj.douban.mtprovider.SelectProvider;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public class BookFragmentPresenter implements IBookFragmentPresenter, IBookModel,IMusicFragmentModel {

    private Context mContext;
    private IBookFragmentView iBookFragmentView;
    private Items items;

    public BookFragmentPresenter(Context mContext, IBookFragmentView iBookFragmentView) {
        this.mContext = mContext;
        this.iBookFragmentView = iBookFragmentView;
    }

    @Override
    public void doRegisterMultitypeItem() {
        items = new Items();
        MultiTypeAdapter adapter = new MultiTypeAdapter(items);
        adapter.register(NormalItem.class, new NormalProvider(mContext));
        adapter.register(ContentIconItem.class, new ContentIconProvider(mContext));
        adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.BOOK_SELECT_BOOK_INDEX));
        iBookFragmentView.onRegisterMultitypeItem(adapter);
    }

    @Override
    public void doInitLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        iBookFragmentView.onInitLayoutManager(manager);
    }

    @Override
    public void doConnectHttp() {
        new BookFragmentModel(mContext, this);
    }

    @Override
    public void onConnectStart() {
        iBookFragmentView.onStartVisibility(View.VISIBLE, View.GONE);
    }

    @Override
    public void onConnectError() {
        iBookFragmentView.onErrorVisibility(View.GONE, View.VISIBLE);
    }

    @Override
    public void onConnectCompleted() {
        iBookFragmentView.onCompletedVisibility(View.GONE, View.GONE);
    }

    @Override
    public void onConnectNext(List<String> newBookData, List<View> newBookData1, List<String> newBookData2) {
        items.add(new NormalItem(newBookData, "新书速递", MyConstants.BOOK_NEW_BOOK_INDEX));
        items.add(new NormalItem(newBookData, "虚构类图书", MyConstants.BOOK_FICTION_BOOK_INDEX));
        items.add(new NormalItem(newBookData, "非虚构类图书", MyConstants.BOOK_NOFICTION_BOOK_INDEX));
        items.add(new ContentIconItem(newBookData1, "热点", MyConstants.BOOK_CONTENT_ICON_INDEX));
        items.add(new NormalItem(newBookData, "畅销书籍", MyConstants.BOOK_BESTSELLER_BOOK_INDEX));
        items.add(new MayYouLikeItem(newBookData2, "你可能感兴趣", MyConstants.BOOK_MAY_YOU_LIKE_BOOK_INDEX));
        items.add(new SelectItem("选图书"));
        iBookFragmentView.onSetAdapter();
    }

    @Override
    public void onConnectNext(List<String> newMusicData, List<String> mandoPopMusicData, List<String> westernMusicData, List<String> jSKMusicData, List<String> mayYouLikeMusicData) {

    }
}
