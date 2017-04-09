package com.doubanapp.hbj.douban.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.doubanapp.hbj.douban.IModel.IBookModel;
import com.doubanapp.hbj.douban.IModel.IMovieModel;
import com.doubanapp.hbj.douban.IModel.IMusicModel;
import com.doubanapp.hbj.douban.IPresenter.IFragmentPresenter;
import com.doubanapp.hbj.douban.IView.IFragmentBaseView;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.model.BookFragmentModel;
import com.doubanapp.hbj.douban.model.MovieFragmentModel;
import com.doubanapp.hbj.douban.model.MusicFragmentModel;
import com.doubanapp.hbj.douban.mtitem.ButtomItem;
import com.doubanapp.hbj.douban.mtitem.ContentIconItem;
import com.doubanapp.hbj.douban.mtitem.ContentTitleViewPagerItem;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;
import com.doubanapp.hbj.douban.mtitem.MovieListSelectionItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.mtitem.SelectItem;
import com.doubanapp.hbj.douban.mtprovider.ButtomProvider;
import com.doubanapp.hbj.douban.mtprovider.ContentIconProvider;
import com.doubanapp.hbj.douban.mtprovider.ContentTitleViewPagerProvider;
import com.doubanapp.hbj.douban.mtprovider.MayYouLikeProvider;
import com.doubanapp.hbj.douban.mtprovider.MovieListSelectionProvider;
import com.doubanapp.hbj.douban.mtprovider.NormalProvider;
import com.doubanapp.hbj.douban.mtprovider.SelectProvider;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 电影，书籍，音乐的Presenter
 * Created by Administrator on 2017/4/7 0007.
 */
public class FragmentPresenter implements IFragmentPresenter, IMovieModel, IBookModel, IMusicModel {

    private Context mContext;
    private IFragmentBaseView iFragmentBaseView;
    private Items items;

    public FragmentPresenter(Context mContext, IFragmentBaseView iFragmentBaseView) {
        this.mContext = mContext;
        this.iFragmentBaseView = iFragmentBaseView;
    }

    /*
    * 注册*/
    @Override
    public void doRegisterMultitypeItem(int selectPage) {
        items = new Items();
        MultiTypeAdapter adapter = new MultiTypeAdapter(items);
        adapter.register(NormalItem.class, new NormalProvider(mContext));
        adapter.register(ContentIconItem.class, new ContentIconProvider(mContext));
        //注册各个页面特有布局
        switch (selectPage) {
            case MyConstants.MUSIC_REGISTER_PAGE_INDEX://音乐
                adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
                adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MUSIC_SELECT_MUSIC_INDEX));
                break;
            case MyConstants.BOOK_REGISTER_PAGE_INDEX://书籍
                adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
                adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.BOOK_SELECT_BOOK_INDEX));
                break;
            case MyConstants.MOVIE_REGISTER_PAGE_INDEX://电影
                adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
                adapter.register(MovieListSelectionItem.class, new MovieListSelectionProvider(mContext));
                adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MOVIE_SELECT_MOVIE_INDEX));
                break;
            case MyConstants.HOME_DAYRECOMMEND_REGISTER_PAGE_INDEX://主页每日推荐
                adapter.register(ContentTitleViewPagerItem.class, new ContentTitleViewPagerProvider());
                adapter.register(ButtomItem.class, new ButtomProvider());
                break;
            default:
        }
        iFragmentBaseView.onRegisterMultitypeItem(adapter);
    }

    /*
    * layoutManager*/
    @Override
    public void doInitLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        iFragmentBaseView.onInitLayoutManager(manager);
    }

    /*
    * 连接网络获取数据*/
    @Override
    public void doConnectHttp(int selectPage) {
        switch (selectPage) {
            case MyConstants.MUSIC_REGISTER_PAGE_INDEX:
                new MusicFragmentModel(mContext, this);//获取音乐数据
                break;
            case MyConstants.BOOK_REGISTER_PAGE_INDEX:
                new BookFragmentModel(mContext, this);//获取书籍数据
                break;
            case MyConstants.MOVIE_REGISTER_PAGE_INDEX:
                new MovieFragmentModel(mContext, this);//获取电影数据
                break;
            default:
        }

    }

    @Override
    public void onConnectStart() {
        iFragmentBaseView.onStartVisibility(View.VISIBLE, View.GONE);
    }

    @Override
    public void onConnectError() {
        iFragmentBaseView.onErrorVisibility(View.GONE, View.VISIBLE);
    }

    @Override
    public void onConnectCompleted() {
        iFragmentBaseView.onCompletedVisibility(View.GONE, View.GONE);
        iFragmentBaseView.onSetAdapter();
    }

    /*
    * 书籍数据*/
    @Override
    public void onBookConnectNext(List<String> newBookData, List<View> newBookData1, List<String> newBookData2) {
        items.add(new NormalItem(newBookData, "新书速递", MyConstants.BOOK_NEW_BOOK_INDEX));
        items.add(new NormalItem(newBookData, "虚构类图书", MyConstants.BOOK_FICTION_BOOK_INDEX));
        items.add(new ContentIconItem(newBookData1, "热点", MyConstants.BOOK_CONTENT_ICON_INDEX));
        items.add(new NormalItem(newBookData, "非虚构类图书", MyConstants.BOOK_NOFICTION_BOOK_INDEX));
        items.add(new NormalItem(newBookData, "畅销书籍", MyConstants.BOOK_BESTSELLER_BOOK_INDEX));
        items.add(new MayYouLikeItem(newBookData2, "你可能感兴趣", MyConstants.BOOK_MAY_YOU_LIKE_BOOK_INDEX));
        items.add(new SelectItem("选图书"));
    }

    /*
    * 音乐数据*/
    @Override
    public void onMusicConnectNext(List<String> newMusicData, List<View> musicHotData, List<String> mandoPopMusicData, List<String> westernMusicData, List<String> jSKMusicData, List<String> mayYouLikeMusicData) {
        items.add(new NormalItem(newMusicData, "新曲", MyConstants.MUSIC_NEW_MUSIC_INDEX));
        items.add(new ContentIconItem(musicHotData, "热点", MyConstants.MUSIC_CONTENT_ICON_INDEX));
        items.add(new NormalItem(mandoPopMusicData, "华语歌曲", MyConstants.MUSIC_HUAYU_MUSIC_INDEX));
        items.add(new NormalItem(westernMusicData, "欧美歌曲", MyConstants.MUSIC_OUMEI_MUSIC_INDEX));
        items.add(new NormalItem(jSKMusicData, "日韩歌曲", MyConstants.MUSIC_RIHAN_MUSIC_INDEX));
        items.add(new MayYouLikeItem(mayYouLikeMusicData, "你可能感兴趣", MyConstants.MUSIC_MAY_YOU_LIKE_MUSIC_INDEX));
        items.add(new SelectItem("选音乐"));
    }

    /*
    * 电影数据*/
    @Override
    public void onMovieConnectNext(List<String> movieHotShowData, List<String> movieComingSoonData, List<String> movieListSelectionData, List<String> movieMayYouLikeData, List<View> movieSelectData) {
        items.add(new NormalItem(movieHotShowData, "正在热映", MyConstants.MOVIE_HOT_SHOW_INDEX));
        items.add(new ContentIconItem(movieSelectData, "热点", MyConstants.MOVIE_CONTENT_ICON_INDEX));
        items.add(new NormalItem(movieComingSoonData, "即将上映", MyConstants.MOVIE_COMING_SOON_INDEX));
        items.add(new MovieListSelectionItem(movieListSelectionData, "榜单精选"));
        items.add(new MayYouLikeItem(movieMayYouLikeData, "你可能感兴趣", MyConstants.MOVIE_MAY_YOU_LIKE_INDEX));
        items.add(new SelectItem("选电影"));
    }
}
