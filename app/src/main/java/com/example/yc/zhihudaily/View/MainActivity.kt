package com.example.yc.zhihudaily.View

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.example.yc.zhihudaily.Model.Datas
import com.example.yc.zhihudaily.Presenter.Contract
import com.example.yc.zhihudaily.Presenter.ListAdapter
import com.example.yc.zhihudaily.Presenter.MainPresenter
import com.example.yc.zhihudaily.R

import java.util.ArrayList

class MainActivity : AppCompatActivity(), Contract.ViewPresenter {

    internal var isLoading = false
    internal var data = Datas()
    internal lateinit var listAdapter: ListAdapter
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var listPresenter: Contract.ListPresenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listPresenter.getData()

    }

    fun initLayout() {
        listAdapter = ListAdapter(this, data)
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = listAdapter
    }

    override fun initView() {
        initLayout()
        setUpLoad()
        setUpRefresh()
        listAdapter.initData(data)
        swipeRefreshLayout.isRefreshing = false
        listAdapter.notifyDataSetChanged()
    }

    override fun updateView() {
        listAdapter.addData(data)
        listAdapter.notifyDataSetChanged()
    }

    override fun setData(datas: Datas) {
        this.data = datas
    }

    override fun refreshView() {
        initLayout()
        listAdapter.initData(data)
        swipeRefreshLayout.isRefreshing = false
        listAdapter.notifyDataSetChanged()
    }

    /**
     * 设置上拉加载
     */
    private fun setUpLoad() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val layoutManager = recyclerView.layoutManager
                val visibleCount = layoutManager!!.childCount
                val totalCount = layoutManager.itemCount
                val lastVisiableItemPos = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (visibleCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisiableItemPos >= totalCount - 1
                        && !isLoading) {
                    listPresenter.loadMore(data.date)
                }
            }
        })
    }

    /**
     * 设置下拉刷新
     */
    private fun setUpRefresh() {
        swipeRefreshLayout.setOnRefreshListener { listPresenter.refreshData() }
    }
}