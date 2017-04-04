package com.doubanapp.hbj.douban.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.doubanapp.hbj.douban.IPresenter.IMusicFragmentPresenter;
import com.doubanapp.hbj.douban.IView.IMusicFragmentView;
import com.doubanapp.hbj.douban.R;
import com.doubanapp.hbj.douban.bean.KuaiDiJsonData;
import com.doubanapp.hbj.douban.constants.MyConstants;
import com.doubanapp.hbj.douban.interf.MyServiceInterface;
import com.doubanapp.hbj.douban.mtitem.MayYouLikeItem;
import com.doubanapp.hbj.douban.mtitem.NormalItem;
import com.doubanapp.hbj.douban.mtitem.SelectItem;
import com.doubanapp.hbj.douban.mtprovider.MayYouLikeProvider;
import com.doubanapp.hbj.douban.mtprovider.NormalProvider;
import com.doubanapp.hbj.douban.mtprovider.SelectProvider;
import com.doubanapp.hbj.douban.utils.BoubanAPIConnectCountAlert;
import com.doubanapp.hbj.douban.utils.MyUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/4/3 0003.
 */
public class MusicFragmentPresenter implements IMusicFragmentPresenter {

    private List<String> mData1 = new ArrayList<>();
    private List<String> mData2 = new ArrayList<>();
    private List<String> mData3 = new ArrayList<>();
    private List<String> mData4 = new ArrayList<>();
    private List<String> mData5 = new ArrayList<>();
    private Context mContext;
    private IMusicFragmentView iMusicFragmentView;

    public MusicFragmentPresenter(Context mContext, IMusicFragmentView iMusicFragmentView) {
        this.mContext = mContext;
        this.iMusicFragmentView = iMusicFragmentView;
    }

    @Override
    public void doRegisterMultitypeItem() {
        Items items = new Items();
        //MultiTypeAdapter
        MultiTypeAdapter adapter = new MultiTypeAdapter(items);
        adapter.register(NormalItem.class, new NormalProvider(mContext));
        adapter.register(MayYouLikeItem.class, new MayYouLikeProvider(mContext));
        adapter.register(SelectItem.class, new SelectProvider(mContext, MyConstants.MUSIC_SELECT_MUSIC_INDEX));
        iMusicFragmentView.onRegisterMultitypeItem(items, adapter);
    }

    @Override
    public void doInitLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        iMusicFragmentView.onInitLayoutManager(manager);
    }

    @Override
    public void doConnectHttp() {
        String baseUrl = MyUtils.getResourcesString(R.string.base_kuaidi_url);
        //此处加载数据
        Retrofit retrofit = MyUtils.getRetrofit(baseUrl);
        //请求网络
        retrofit.create(MyServiceInterface.class).toSearch("yuantong", "500379523313")
                //ResponseBody数据保存，和转换
                .map(new Func1<ResponseBody, KuaiDiJsonData>() {
                    @Override
                    public KuaiDiJsonData call(ResponseBody responseBody) {

                        String stringJson = null;
                        try {
                            stringJson = responseBody.string();
                            //保存json数据

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return parseJsonData(stringJson);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<KuaiDiJsonData>() {
                    @Override
                    public void onCompleted() {
                        //请求结束
                        iMusicFragmentView.onCompletedVisibility();

                    }

                    @Override
                    public void onError(Throwable e) {
                        //错误回调
                        iMusicFragmentView.onErrorVisibility();
                    }

                    @Override
                    public void onNext(KuaiDiJsonData res) {
                        //成功
                        //MyLogUtils.i(TAG, res.getData().get(0).getContext());
                        String context = res.getData().get(0).getContext();

                        for (int i = 0; i < 10; i++) {
                            mData1.add("新曲");
                        }
                        for (int i = 0; i < 10; i++) {
                            mData2.add("华语");
                        }
                        for (int i = 0; i < 10; i++) {
                            mData3.add("欧美");
                        }
                        for (int i = 0; i < 10; i++) {
                            mData4.add("日韩");
                        }
                        for (int i = 0; i < 6; i++) {
                            mData5.add("感兴趣");
                        }
                        iMusicFragmentView.onConnectHttp(mData1, mData2, mData3, mData4, mData5);
                    }

                    @Override
                    public void onStart() {
                        //开始
                        new BoubanAPIConnectCountAlert(mContext);
                        // MyLogUtils.i(TAG, "onStart");
                        iMusicFragmentView.onStartVisibility();

                    }
                });
    }

    private KuaiDiJsonData parseJsonData(String data) {
        Gson gson = new Gson();
        return gson.fromJson(data, KuaiDiJsonData.class);
    }
}
