package com.example.yc.zhihudaily.Presenter

import android.content.Context
import android.widget.Toast

import com.example.yc.zhihudaily.Model.Datas
import com.example.yc.zhihudaily.Model.NetModel
import kotlinx.coroutines.experimental.android.UI

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.experimental.launch
import java.io.IOException

class MainPresenter(var viewPresenter: Contract.ViewPresenter) : Contract.ListPresenter {
    internal var netModel = NetModel()

    override fun getData() {
        val call = netModel.refresh()
        launch{
            val response:Datas?
            try{
                response = call.execute( ).body()
                viewPresenter.setData(response!!)
                launch (UI){
                    viewPresenter.initView()
                }
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
//        call.enqueue(object : Callback<Datas> {
//            override fun onFailure(call: Call<Datas>, t: Throwable) {
//                Toast.makeText(viewPresenter as Context, "Internet connecting failed", Toast.LENGTH_SHORT).show()
//                t.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Datas>, response: Response<Datas>) {
//                viewPresenter.setData(response.body()!!)
//                viewPresenter.initView()
//            }
//        })
    }

    override fun refreshData() {
        val call = netModel.refresh()
        launch{
            val response:Datas?
            try{
                response = call.execute( ).body()
                viewPresenter.setData(response!!)
                launch (UI){
                    viewPresenter.refreshView()
                }
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
//        call.enqueue(object : Callback<Datas> {
//            override fun onFailure(call: Call<Datas>, t: Throwable) {
//                Toast.makeText(viewPresenter as Context, "Internet connecting failed", Toast.LENGTH_SHORT).show()
//                t.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Datas>, response: Response<Datas>) {
//                viewPresenter.setData(response.body()!!)
//                viewPresenter.refreshView()
//            }
//        })
    }

    override fun loadMore(date: String) {
        val call = netModel.update(date)
        launch{
            val response:Datas?
            try{
                response = call.execute( ).body()
                viewPresenter.setData(response!!)
                launch (UI){
                    viewPresenter.updateView()
                }
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
//        call.enqueue(object : Callback<Datas> {
//            override fun onResponse(call: Call<Datas>, response: Response<Datas>) {
//                viewPresenter.setData(response.body()!!)
//                viewPresenter.updateView()
//            }
//
//            override fun onFailure(call: Call<Datas>, t: Throwable) {
//                Toast.makeText(viewPresenter as Context, "Internet connecting failed", Toast.LENGTH_SHORT).show()
//            }
//        })
    }
}
