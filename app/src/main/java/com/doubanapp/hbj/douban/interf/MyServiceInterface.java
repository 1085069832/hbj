package com.doubanapp.hbj.douban.interf;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
public interface MyServiceInterface {
    @GET("v2/movie/top250")
    Observable<ResponseBody> toConnect(@Query("start") int start, @Query("count") int count);

    @POST("query")
    Observable<ResponseBody> toSearch(@Query("type") String type, @Query("postid") String postid);

    //动态url
    @GET
    Observable<ResponseBody> toConnectGankData(@Url String url);

}
