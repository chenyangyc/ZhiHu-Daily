package com.example.yc.zhihudaily.ConnectingTheInternet

import com.example.yc.zhihudaily.Model.Datas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetApi {
    @get:GET("stories/latest")
    val list: Call<Datas>

    @GET("news/before/{date}")
    fun getList(@Path("date") date: String): Call<Datas>
}
