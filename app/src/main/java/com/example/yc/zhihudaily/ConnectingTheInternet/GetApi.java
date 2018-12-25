package com.example.yc.zhihudaily.ConnectingTheInternet;

import com.example.yc.zhihudaily.Model.Datas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetApi {
    @GET("stories/latest")
    Call<Datas> getList();

    @GET("news/before/{date}")
    Call<Datas> getList(@Path("date") String date);
}
