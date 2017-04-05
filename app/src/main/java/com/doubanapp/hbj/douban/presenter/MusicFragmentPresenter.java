package com.doubanapp.hbj.douban.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.doubanapp.hbj.douban.IPresenter.IMusicFragmentPresenter;
import com.doubanapp.hbj.douban.IView.IMusicFragmentView;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.IModel.IMusicFragmentModel;
import com.doubanapp.hbj.douban.model.MusicFragmentModel;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.mtitem.SelectItem;
import com.doubanapp.hbj.douban.mtprovider.MayYouLikeProvider;
import com.doubanapp.hbj.douban.mtprovider.NormalProvider;
import com.doubanapp.hbj.douban.mtprovider.SelectProvider;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class MusicFragmentPresenter implements IMusicFragmentPresenter, IMusicFragmentModel {

    private Context mContext;
    private IMusicFragmentView iMusicFragmentView;
    private Items items;

    public MusicFragmentPresenter(Context mContext, IMusicFragmentView iMusicFragmentView) {
        this.mContext = mContext;
        this.iMusicFragmentView = iMusicFragmentView;
    }

    @Override
    public void doRegisterMultitypeItem() {
        items = new Items();
        //MultiTypeAdapter
        MultiTypeAdapter adapter = new MultiTypeAdapter(items);
        adapter.register(NormalItem.class, new NormalProvider(mContext));
        adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MUSIC_SELECT_MUSIC_INDEX));
        iMusicFragmentView.onRegisterMultitypeItem(adapter);
    }

    @Override
    public void doInitLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        iMusicFragmentView.onInitLayoutManager(manager);
    }

    @Override
    public void doConnectHttp() {
        new MusicFragmentModel(mContext, this);
    }

    @Override
    public void onConnectStart() {
        iMusicFragmentView.onStartVisibility(View.VISIBLE, View.GONE);
    }

    @Override
    public void onConnectNext(List<String> newMusicData, List<String> mandoPopMusicData, List<String> westernMusicData, List<String> jSKMusicData, List<String> mayYouLikeMusicData) {
        items.add(new NormalItem(newMusicData, "新曲", MyConstants.MUSIC_NEW_MUSIC_INDEX));
        items.add(new NormalItem(mandoPopMusicData, "华语歌曲", MyConstants.MUSIC_HUAYU_MUSIC_INDEX));
        items.add(new NormalItem(westernMusicData, "欧美歌曲", MyConstants.MUSIC_OUMEI_MUSIC_INDEX));
        items.add(new NormalItem(jSKMusicData, "日韩歌曲", MyConstants.MUSIC_RIHAN_MUSIC_INDEX));
        items.add(new MayYouLikeItem(mayYouLikeMusicData, "你可能感兴趣", MyConstants.MUSIC_MAY_YOU_LIKE_MUSIC_INDEX));
        items.add(new SelectItem("选音乐"));
    }

    @Override
    public void onConnectError() {
        iMusicFragmentView.onErrorVisibility(View.GONE,View.VISIBLE);
    }

    @Override
    public void onConnectCompleted() {
        iMusicFragmentView.onCompletedVisibility(View.GONE,View.GONE);
        iMusicFragmentView.onSetAdapter();
    }
}
