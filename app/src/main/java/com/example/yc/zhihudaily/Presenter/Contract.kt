package com.example.yc.zhihudaily.Presenter

import com.example.yc.zhihudaily.Model.Datas

class Contract {

    interface ViewPresenter {
        fun initView()
        fun refreshView()
        fun updateView()
        fun setData(datas: Datas)
    }

    interface ListPresenter {
        fun getData()
        fun refreshData()
        fun loadMore(date: String)
    }
}
