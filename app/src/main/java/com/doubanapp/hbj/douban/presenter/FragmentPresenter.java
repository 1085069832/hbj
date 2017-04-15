package com.doubanapp.hbj.douban.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.doubanapp.hbj.douban.IModel.IBookModel;
import com.doubanapp.hbj.douban.IModel.IHomeAllModel;
import com.doubanapp.hbj.douban.IModel.IHomeDayRecommendModel;
import com.doubanapp.hbj.douban.IModel.IMovieModel;
import com.doubanapp.hbj.douban.IModel.IMusicModel;
import com.doubanapp.hbj.douban.IPresenter.IFragmentPresenter;
import com.doubanapp.hbj.douban.IView.IFragmentBaseView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.model.BookFragmentModel;
import com.doubanapp.hbj.douban.model.HomeAllFragmentModel;
import com.doubanapp.hbj.douban.model.HomeDayRecommendFragmentModel;
import com.doubanapp.hbj.douban.model.MovieFragmentModel;
import com.doubanapp.hbj.douban.model.MusicFragmentModel;
import com.doubanapp.hbj.douban.mtitem.ButtomItem;
import com.doubanapp.hbj.douban.mtitem.ContentIconItem;
import com.doubanapp.hbj.douban.mtitem.ContentTitleViewPagerItem;
import com.doubanapp.hbj.douban.mtitem.HomeAllTitleItem;
import com.doubanapp.hbj.douban.mtitem.HomeNormalItem;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;
import com.doubanapp.hbj.douban.mtitem.MovieListSelectionItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.mtitem.SelectItem;
import com.doubanapp.hbj.douban.mtprovider.ButtomProvider;
import com.doubanapp.hbj.douban.mtprovider.ContentIconProvider;
import com.doubanapp.hbj.douban.mtprovider.ContentTitleViewPagerProvider;
import com.doubanapp.hbj.douban.mtprovider.HomeAllTitleProvider;
import com.doubanapp.hbj.douban.mtprovider.HomeNormalProvider;
import com.doubanapp.hbj.douban.mtprovider.MayYouLikeProvider;
import com.doubanapp.hbj.douban.mtprovider.MovieListSelectionProvider;
import com.doubanapp.hbj.douban.mtprovider.NormalProvider;
import com.doubanapp.hbj.douban.mtprovider.SelectProvider;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.NoneEffect;
import com.mingle.sweetpick.RecyclerViewDelegate;
import com.mingle.sweetpick.SweetSheet;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 电影，书籍，音乐的Presenter
 * Created by Administrator on 2017/4/7 0007.
 */
public class FragmentPresenter implements SweetSheet.OnMenuItemClickListener, IFragmentPresenter, IMovieModel, IBookModel,
        IMusicModel, IHomeDayRecommendModel, IHomeAllModel {

    private Context mContext;
    private IFragmentBaseView iFragmentBaseView;
    private Items items;
    SweetSheet mSweetSheet;
    private RelativeLayout rl;
    private RecyclerView rc;
    private HomeAllFragmentModel homeAllFragmentModel;
    private HomeDayRecommendFragmentModel homeDayRecommendFragmentModel;
    private MovieFragmentModel movieFragmentModel;
    private BookFragmentModel bookFragmentModel;
    private MusicFragmentModel musicFragmentModel;

    public FragmentPresenter(Context mContext, IFragmentBaseView iFragmentBaseView) {
        this.mContext = mContext;
        this.iFragmentBaseView = iFragmentBaseView;
    }

    @Override
    public void doInitSweetSheet(RelativeLayout rl, RecyclerView rc) {
        this.rl = rl;
        this.rc = rc;
        // SweetSheet 控件,根据 rl 确认位置
        mSweetSheet = new SweetSheet(rl);
        //设置数据源 (数据源支持设置 list 数组,也支持从menu 资源中获取)
        mSweetSheet.setMenuList(R.menu.home_all_sweet_menu);
        //根据设置不同的 Delegate 来显示不同的风格.
        mSweetSheet.setDelegate(new RecyclerViewDelegate(true));
        //根据设置不同Effect来设置背景效果:BlurEffect 模糊效果.DimEffect 变暗效果,NoneEffect 没有效果.
        mSweetSheet.setBackgroundEffect(new NoneEffect());
        mSweetSheet.setBackgroundClickEnable(false);
        //设置菜单点击事件
        mSweetSheet.setOnMenuItemClickListener(this);
    }

    /*
    * 注册*/
    @Override
    public void doRegisterMultitypeItem() {
        items = new Items();
        MultiTypeAdapter adapter = new MultiTypeAdapter(items);
        //注册
        adapter.register(NormalItem.class, new NormalProvider(mContext));
        adapter.register(ContentIconItem.class, new ContentIconProvider(mContext));
        adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MUSIC_SELECT_MUSIC_INDEX));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.BOOK_SELECT_BOOK_INDEX));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MOVIE_SELECT_MOVIE_INDEX));
        adapter.register(MovieListSelectionItem.class, new MovieListSelectionProvider(mContext));
        adapter.register(ContentTitleViewPagerItem.class, new ContentTitleViewPagerProvider());
        adapter.register(ButtomItem.class, new ButtomProvider());
        adapter.register(HomeNormalItem.class, new HomeNormalProvider());
        adapter.register(HomeAllTitleItem.class, new HomeAllTitleProvider(mSweetSheet, rc));
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
            case MyConstants.MUSIC_PRESENTER_PAGE_INDEX://获取音乐数据
                musicFragmentModel = new MusicFragmentModel(mContext, this);
                musicFragmentModel.toConnectHttp();
                break;
            case MyConstants.BOOK_PRESENTER_PAGE_INDEX://获取书籍数据
                bookFragmentModel = new BookFragmentModel(mContext, this);
                bookFragmentModel.toConnectData();
                break;
            case MyConstants.MOVIE_PRESENTER_PAGE_INDEX://获取电影数据
                movieFragmentModel = new MovieFragmentModel(mContext, this);
                movieFragmentModel.toConnectData();
                break;
            case MyConstants.HOME_DAYRECOMMEND_PRESENTER_PAGE_INDEX://获取主页每日推荐
                homeDayRecommendFragmentModel = new HomeDayRecommendFragmentModel(mContext, this);
                homeDayRecommendFragmentModel.toConnectHttp();
                break;
            case MyConstants.HOME_ALL_PRESENTER_PAGE_INDEX://获取主页all
                homeAllFragmentModel = new HomeAllFragmentModel(mContext, this);
                homeAllFragmentModel.toConnectData();
                break;
            default:
        }

    }

    /*
    * 取消网络请求*/
    @Override
    public void doDestroy() {
        if (musicFragmentModel != null) musicFragmentModel.cancelHttpRequset();
        if (bookFragmentModel != null) bookFragmentModel.cancelHttpRequset();
        if (movieFragmentModel != null) movieFragmentModel.cancelHttpRequset();
        if (homeDayRecommendFragmentModel != null)
            homeDayRecommendFragmentModel.cancelHttpRequset();
        if (homeAllFragmentModel != null) homeAllFragmentModel.cancelHttpRequset();
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
        iFragmentBaseView.onSetMTAdapter();
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
    public void onHomeDayRecommendConnectNext(List<View> vpTitleData, List<String> androidData, List<String> frontData, List<String> iosData, List<String> appData, List<View> restData, List<View> moreRecommendData, List<View> welFareData) {
        items.add(new ContentTitleViewPagerItem(vpTitleData, MyConstants.HOME_CONTENT_TITLE_VP_INDEX));
        items.add(new NormalItem(androidData, "Android", MyConstants.HOME_ANDROID_INDEX));
        items.add(new NormalItem(iosData, "iOS", MyConstants.HOME_IOS_INDEX));
        items.add(new ContentIconItem(moreRecommendData, "更多推荐", MyConstants.HOME_CONTENT_MORE_RECOMMEND_ICON_INDEX));
        items.add(new NormalItem(frontData, "前端", MyConstants.HOME_FRONT_INDEX));
        items.add(new NormalItem(appData, "App", MyConstants.HOME_APP_INDEX));
        items.add(new ContentIconItem(restData, "休息视频", MyConstants.HOME_CONTENT_REST_ICON_INDEX));
        items.add(new ContentIconItem(welFareData, "福利", MyConstants.HOME_CONTENT_WELFARE_ICON_INDEX));
        items.add(new ButtomItem());
    }

    /*
    * homeall数据*/
    @Override
    public void onHomeAllConnectNext(List<String> allData) {
        items.add(new HomeAllTitleItem(""));
        for (int i = 0; i < allData.size(); i++) {
            items.add(new HomeNormalItem(""));
        }
    }

    /*
    * sweet条目选中*/
    @Override
    public boolean onItemClick(int position, MenuEntity menuEntity) {
        switch (position) {
            case 0:

                break;
            default:
        }
        //homeAllFragmentModel.toConnectData();
        Toast.makeText(MyUtils.getContext(), menuEntity.title + "  " + position, Toast.LENGTH_SHORT).show();
        return true;
    }
}
