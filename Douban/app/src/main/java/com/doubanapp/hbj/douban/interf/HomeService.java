package com.doubanapp.hbj.douban.interf;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/19 0019.
 */
public interface HomeService {
    @GET("v2/movie/top250")
    Observable<ResponseBody> toConnect(@Query("start") int start, @Query("count") int count);

    @POST("query")
    Observable<ResponseBody> toSearch(@Query("type") String type, @Query("postid") String postid);
}
