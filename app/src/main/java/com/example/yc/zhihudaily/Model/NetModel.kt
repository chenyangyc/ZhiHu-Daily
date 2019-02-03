package com.example.yc.zhihudaily.Model

import com.example.yc.zhihudaily.ConnectingTheInternet.GetApi

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetModel {

    internal var okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .connectTimeout(9999, TimeUnit.MILLISECONDS)
            .build()

    internal var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    internal var api = retrofit.create(GetApi::class.java)

    fun refresh(): Call<Datas> {
        return api.list
    }

    fun update(date: String): Call<Datas> {
        return api.getList(date)
    }

    companion object {
        private val BASE_URL = "https://news-at.zhihu.com/api/3/"
    }

}
