package com.example.yc.zhihudaily.Model;

import com.example.yc.zhihudaily.ConnectingTheInternet.GetApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetModel {
    private static final String BASE_URL = "https://news-at.zhihu.com/api/3/";

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .connectTimeout(9999, TimeUnit.MILLISECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();

    GetApi api = retrofit.create(GetApi.class);

    public Call<Datas> refresh() {
        return api.getList();
    }

    public Call<Datas> update(String date) {
        return api.getList(date);
    }

}
