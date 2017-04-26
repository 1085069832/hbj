package com.doubanapp.hbj.douban.presenter;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.doubanapp.hbj.douban.IModel.IBookModel;
import com.doubanapp.hbj.douban.IModel.IHomeAllModel;
import com.doubanapp.hbj.douban.IModel.IHomeAndroidModel;
import com.doubanapp.hbj.douban.IModel.IHomeDayRecommendModel;
import com.doubanapp.hbj.douban.IModel.IHomeWelFareModel;
import com.doubanapp.hbj.douban.IModel.IMovieModel;
import com.doubanapp.hbj.douban.IModel.IMusicModel;
import com.doubanapp.hbj.douban.IPresenter.IFragmentPresenter;
import com.doubanapp.hbj.douban.IView.IFragmentBaseView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.activity.MainActivity;
import com.doubanapp.hbj.douban.bean.HomeDayRecommendJsonData;
import com.doubanapp.hbj.douban.bean.HomeJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.model.BookFragmentModel;
import com.doubanapp.hbj.douban.model.HomeAllFragmentModel;
import com.doubanapp.hbj.douban.model.HomeAndroidFragmentModel;
import com.doubanapp.hbj.douban.model.HomeDayRecommendFragmentModel;
import com.doubanapp.hbj.douban.model.HomeWelFareFragmentModel;
import com.doubanapp.hbj.douban.model.MovieFragmentModel;
import com.doubanapp.hbj.douban.model.MusicFragmentModel;
import com.doubanapp.hbj.douban.mtitem.ContentIconItem;
import com.doubanapp.hbj.douban.mtitem.ContentTitleViewPagerItem;
import com.doubanapp.hbj.douban.mtitem.HomeAllTitleItem;
import com.doubanapp.hbj.douban.mtitem.HomeNormalItem;
import com.doubanapp.hbj.douban.mtitem.HomeWelFareItem;
import com.doubanapp.hbj.douban.mtitem.LoadMoreItem;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;
import com.doubanapp.hbj.douban.mtitem.MovieListSelectionItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.mtitem.SelectItem;
import com.doubanapp.hbj.douban.mtitem.TopItem;
import com.doubanapp.hbj.douban.mtprovider.ContentIconProvider;
import com.doubanapp.hbj.douban.mtprovider.ContentTitleViewPagerProvider;
import com.doubanapp.hbj.douban.mtprovider.HomeAllTitleProvider;
import com.doubanapp.hbj.douban.mtprovider.HomeNormalProvider;
import com.doubanapp.hbj.douban.mtprovider.HomeWelFareProvider;
import com.doubanapp.hbj.douban.mtprovider.LoadMoreProvider;
import com.doubanapp.hbj.douban.mtprovider.MayYouLikeProvider;
import com.doubanapp.hbj.douban.mtprovider.MovieListSelectionProvider;
import com.doubanapp.hbj.douban.mtprovider.NormalProvider;
import com.doubanapp.hbj.douban.mtprovider.SelectProvider;
import com.doubanapp.hbj.douban.mtprovider.TopProvider;
import com.doubanapp.hbj.douban.utils.MyLogUtils;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 电影，书籍，音乐的Presenter
 * Created by Administrator on 2017/4/7 0007.
 */
public class FragmentPresenter implements MenuSheetView.OnMenuItemClickListener, IFragmentPresenter, IMovieModel, IBookModel,
        IMusicModel, IHomeDayRecommendModel, IHomeAllModel, IHomeAndroidModel, IHomeWelFareModel {

    public static final String TAG = "FragmentPresenter";
    private Context mContext;
    private IFragmentBaseView iFragmentBaseView;
    private Items items;
    private HomeAllFragmentModel homeAllFragmentModel;
    private HomeDayRecommendFragmentModel homeDayRecommendFragmentModel;
    private MovieFragmentModel movieFragmentModel;
    private BookFragmentModel bookFragmentModel;
    private MusicFragmentModel musicFragmentModel;
    private HomeAndroidFragmentModel homeAndroidFragmentModel;
    private HomeWelFareFragmentModel homeWelFareFragmentModel;
    private MultiTypeAdapter adapter;
    private MenuSheetView menuSheetView;
    private BottomSheetLayout bottomSheet;


    public FragmentPresenter(Context mContext, IFragmentBaseView iFragmentBaseView) {
        this.mContext = mContext;
        this.iFragmentBaseView = iFragmentBaseView;
    }

    /*
    * home all 分类*/
    @Override
    public void doInitBottomSheet(final BottomSheetLayout bottomSheet) {
        this.bottomSheet = bottomSheet;
        menuSheetView = new MenuSheetView(mContext, MenuSheetView.MenuType.LIST, R.string.home_all_bottom_sheet_text, this);
        menuSheetView.inflateMenu(R.menu.home_all_sweet_menu);
    }

    /*
    * 注册*/
    @Override
    public void doRegisterMultitypeItem(RecyclerView rc) {
        items = new Items();
        adapter = new MultiTypeAdapter(items);
        //注册
        adapter.register(NormalItem.class, new NormalProvider(mContext));
        adapter.register(ContentIconItem.class, new ContentIconProvider(mContext));
        adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MUSIC_SELECT_MUSIC_INDEX));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.BOOK_SELECT_BOOK_INDEX));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MOVIE_SELECT_MOVIE_INDEX));
        adapter.register(MovieListSelectionItem.class, new MovieListSelectionProvider(mContext));
        adapter.register(ContentTitleViewPagerItem.class, new ContentTitleViewPagerProvider());
        adapter.register(TopItem.class, new TopProvider());
        adapter.register(LoadMoreItem.class, new LoadMoreProvider());
        adapter.register(HomeNormalItem.class, new HomeNormalProvider(mContext));
        adapter.register(HomeAllTitleItem.class, new HomeAllTitleProvider(mContext));
        adapter.register(HomeWelFareItem.class, new HomeWelFareProvider());
        iFragmentBaseView.onRegisterMultitypeItem(adapter);
    }

    /*
    * layoutManager*/
    @Override
    public void doInitLinearLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        iFragmentBaseView.onInitLayoutManager(manager);
    }

    @Override
    public void doInitStaggeredGridLayoutManager() {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        iFragmentBaseView.onInitLayoutManager(manager);
    }

    /*
    * 连接网络获取数据*/
    @Override
    public void doConnectHttp(int selectPage, boolean isRefresh, boolean isLoadMore) {
        switch (selectPage) {
            case MyConstants.MUSIC_PRESENTER_PAGE_INDEX://获取音乐数据
                if (musicFragmentModel == null)
                    musicFragmentModel = new MusicFragmentModel(mContext, this);
                musicFragmentModel.toConnectHttp();
                break;
            case MyConstants.BOOK_PRESENTER_PAGE_INDEX://获取书籍数据
                if (bookFragmentModel == null)
                    bookFragmentModel = new BookFragmentModel(mContext, this);
                bookFragmentModel.toConnectData();
                break;
            case MyConstants.MOVIE_PRESENTER_PAGE_INDEX://获取电影数据
                if (movieFragmentModel == null)
                    movieFragmentModel = new MovieFragmentModel(mContext, this);
                movieFragmentModel.toConnectData();
                break;
            case MyConstants.HOME_DAYRECOMMEND_PRESENTER_PAGE_INDEX://获取主页每日推荐数据
                if (homeDayRecommendFragmentModel == null)
                    homeDayRecommendFragmentModel = new HomeDayRecommendFragmentModel(mContext, this);
                homeDayRecommendFragmentModel.toConnectHttp(isRefresh, isLoadMore);
                break;
            case MyConstants.HOME_ALL_PRESENTER_PAGE_INDEX://获取主页all数据
                if (homeAllFragmentModel == null)
                    homeAllFragmentModel = new HomeAllFragmentModel(mContext, this);
                homeAllFragmentModel.toConnectData("all");
                break;
            case MyConstants.HOME_ANDROID_PRESENTER_PAGE_INDEX://获取主页android数据
                if (homeAndroidFragmentModel == null)
                    homeAndroidFragmentModel = new HomeAndroidFragmentModel(mContext, this);
                homeAndroidFragmentModel.toConnectData();
                break;
            case MyConstants.HOME_WELFARE_PRESENTER_PAGE_INDEX://获取主页福利数据
                if (homeWelFareFragmentModel == null)
                    homeWelFareFragmentModel = new HomeWelFareFragmentModel(mContext, this);
                homeWelFareFragmentModel.toConnectData();
                break;
            default:
        }
    }

    /*
    * 取消rxjava订阅*/
    @Override
    public void doDestroy() {
        if (musicFragmentModel != null) musicFragmentModel.cancelHttpRequset();
        if (bookFragmentModel != null) bookFragmentModel.cancelHttpRequset();
        if (movieFragmentModel != null) movieFragmentModel.cancelHttpRequset();
        if (homeDayRecommendFragmentModel != null)
            homeDayRecommendFragmentModel.cancelHttpRequset();
        if (homeAllFragmentModel != null) homeAllFragmentModel.cancelHttpRequset();
        if (homeAndroidFragmentModel != null) homeAndroidFragmentModel.cancelHttpRequset();
        if (homeWelFareFragmentModel != null) homeWelFareFragmentModel.cancelHttpRequset();
    }

    /*
    * 开始请求*/
    @Override
    public void onConnectStart(boolean isRefresh, boolean isLoadMore) {
        if (isRefresh) {
            iFragmentBaseView.onStartVisibility(View.GONE, View.GONE);
        } else if (isLoadMore) {
            iFragmentBaseView.onStartVisibility(View.GONE, View.GONE);
            if (!items.isEmpty()) {//不为空，先删除加载更多，再重新添加
                items.remove(items.size() - 1);
                items.add(items.size(), new LoadMoreItem("正在加载"));
                iFragmentBaseView.onNotifyDataSetChanged();
            }
        } else {
            //第一次加载
            iFragmentBaseView.onStartVisibility(View.VISIBLE, View.GONE);
        }
    }

    /*
    * 请求错误*/
    @Override
    public void onConnectError() {
        iFragmentBaseView.onErrorVisibility(View.GONE, View.VISIBLE);
        //为空就不是加载更多的错误返回，本身没有加载更多的界面，错误的返回一定为空，不需要操作加载更多
        //不为空则是加载更多的错误返回，先删除加载更多条目再添加
        if (!items.isEmpty()) {
            items.remove(items.size() - 1);
            items.add(items.size(), new LoadMoreItem("上拉加载"));
            iFragmentBaseView.onNotifyDataSetChanged();
        }
        MyUtils.showAppMsg((MainActivity) mContext, R.string.appmsg_connect_error_text, Color.RED);
    }

    /*
    * 请求完成*/
    @Override
    public void onConnectCompleted() {
        iFragmentBaseView.onCompletedVisibility(View.GONE, View.GONE);
        iFragmentBaseView.onNotifyDataSetChanged();
    }

    /*刷新状态
    * >0表示有新数据，=-1表示刷新失败 ，0表示没有新数据*/
    @Override
    public void onRefreshResult(int resultCount) {
        MyLogUtils.i(TAG, "resultCount" + resultCount);
        if (resultCount > 0) {
            MyUtils.showAppMsg((MainActivity) mContext, MyUtils.getResourcesString(R.string.appmsg_refresh_has_data_text_header) + resultCount +
                    MyUtils.getResourcesString(R.string.appmsg_refresh_has_data_text_footer), Color.parseColor("#3333ff"));
        } else if (resultCount == -1) {
            MyUtils.showAppMsg((MainActivity) mContext, R.string.appmsg_connect_error_text, Color.RED);
        } else if (resultCount == 0) {
            MyUtils.showAppMsg((MainActivity) mContext, R.string.appmsg_refresh_no_data_text, Color.LTGRAY);
        }
        iFragmentBaseView.onRefreshCompleted();
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

    /*
    * home每日推荐*/
    @Override
    public void onHomeDayRecommendConnectNext(HomeDayRecommendJsonData res, String day, boolean isRefresh) {
        //items.add(new ContentTitleViewPagerItem(vpTitleData, MyConstants.HOME_DR_CONTENT_TITLE_VP_INDEX));
        //每日推荐安卓
        if (isRefresh) {
            Items itemsRefresh = new Items();
            itemsRefresh.add(new TopItem(day));
            if (res.getResults().getAndroid() != null && res.getResults().getAndroid().size() >= 2)
                itemsRefresh.add(new NormalItem(res, "Android", MyConstants.HOME_DR_ANDROID_INDEX));
            if (res.getResults().getIOS() != null && res.getResults().getIOS().size() >= 2)
                itemsRefresh.add(new NormalItem(res, "iOS", MyConstants.HOME_DR_IOS_INDEX));
            if (res.getResults().get福利() != null)
                itemsRefresh.add(new ContentIconItem(res.getResults().get福利().get(0).getUrl(), "福利", MyConstants.HOME_DR_WELFARE_INDEX));
            if (res.getResults().get拓展资源() != null && res.getResults().get拓展资源().size() >= 2)
                itemsRefresh.add(new NormalItem(res, "扩展资源", MyConstants.HOME_DR_EXTENDS_RESOURCE_INDEX));
            if (res.getResults().get前端() != null && res.getResults().get前端().size() >= 2)
                itemsRefresh.add(new NormalItem(res, "前端", MyConstants.HOME_DR_FRONT_INDEX));
            if (res.getResults().get休息视频() != null)
                itemsRefresh.add(new HomeNormalItem(res, "休息视频", MyConstants.HOME_DR_REST_INDEX));
            if (res.getResults().getApp() != null && res.getResults().getApp().size() >= 2)
                itemsRefresh.add(new NormalItem(res, "App", MyConstants.HOME_DR_APP_INDEX));
            items.addAll(0, itemsRefresh);
        } else {
            if (!items.isEmpty())//不为空，先删除加载更多条目再添加
                items.remove(items.size() - 1);
            items.add(new TopItem(day));
            if (res.getResults().getAndroid() != null && res.getResults().getAndroid().size() >= 2)
                items.add(new NormalItem(res, "Android", MyConstants.HOME_DR_ANDROID_INDEX));
            if (res.getResults().getIOS() != null && res.getResults().getIOS().size() >= 2)
                items.add(new NormalItem(res, "iOS", MyConstants.HOME_DR_IOS_INDEX));
            if (res.getResults().get福利() != null)
                items.add(new ContentIconItem(res.getResults().get福利().get(0).getUrl(), "福利", MyConstants.HOME_DR_WELFARE_INDEX));
            if (res.getResults().get拓展资源() != null && res.getResults().get拓展资源().size() >= 2)
                items.add(new NormalItem(res, "扩展资源", MyConstants.HOME_DR_EXTENDS_RESOURCE_INDEX));
            if (res.getResults().get前端() != null && res.getResults().get前端().size() >= 2)
                items.add(new NormalItem(res, "前端", MyConstants.HOME_DR_FRONT_INDEX));
            if (res.getResults().get休息视频() != null)
                items.add(new HomeNormalItem(res, "休息视频", MyConstants.HOME_DR_REST_INDEX));
            if (res.getResults().getApp() != null && res.getResults().getApp().size() >= 2)
                items.add(new NormalItem(res, "App", MyConstants.HOME_DR_APP_INDEX));
            items.add(items.size(), new LoadMoreItem("上拉加载"));
        }
    }

    /*
    * home all数据*/
    @Override
    public void onHomeAllConnectNext(HomeJsonData res) {
        items.add(new HomeAllTitleItem("", bottomSheet, menuSheetView));
        for (int position = 0; position < res.getResults().size(); position++) {
            if ("福利".equals(res.getResults().get(position).getType())) {
                items.add(new ContentIconItem(res.getResults().get(position).getUrl(), "福利", MyConstants.HOME_DR_WELFARE_INDEX));
            } else {
                List<String> images = res.getResults().get(position).getImages();
                items.add(new HomeNormalItem(res.getResults().get(position).getType(),
                        res.getResults().get(position).getDesc(),
                        res.getResults().get(position).getWho(),
                        res.getResults().get(position).getPublishedAt(),
                        images == null ? null : images.get(0),
                        position, MyConstants.HOME_ALL_PRESENTER_PAGE_INDEX));
            }
        }
        MyLogUtils.i(TAG, res.getResults().get(1).getDesc());
    }

    /*
    * home Android数据*/
    @Override
    public void onHomeAndroidConnectNext(List<String> androidData) {
        for (int i = 0; i < androidData.size(); i++) {
            items.add(new HomeNormalItem("", i));
        }
    }

    /*
    * home 福利数据*/
    @Override
    public void onHomeWelFareConnectNext(List<String> mData) {
        for (int i = 0; i < mData.size(); i++) {
            int addHeight = (int) (Math.random() * 50) + 260;//随机高度
            items.add(new HomeWelFareItem(mData, addHeight));
        }
    }

    /*
    * home all bottom sheet*/
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(mContext, item.getTitle(), Toast.LENGTH_SHORT).show();
        if (bottomSheet.isSheetShowing()) {
            bottomSheet.dismissSheet();
        }
        return true;
    }
}
