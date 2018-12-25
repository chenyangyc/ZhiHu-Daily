package com.example.yc.zhihudaily.View;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yc.zhihudaily.Model.Datas;
import com.example.yc.zhihudaily.Presenter.Contract;
import com.example.yc.zhihudaily.Presenter.ListAdapter;
import com.example.yc.zhihudaily.Presenter.MainPresenter;
import com.example.yc.zhihudaily.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.ViewPresenter {

    boolean isLoading = false;
    Datas data = new Datas();
    ListAdapter listAdapter;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    public Contract.ListPresenter listPresenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPresenter.getData();

    }

    public void initLayout(){
        listAdapter = new ListAdapter(this, data);
        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void initView(){
        initLayout();
        setUpLoad();
        setUpRefresh();
        listAdapter.stories.clear();
        listAdapter.topStories.clear();
        listAdapter.initData(data);
        swipeRefreshLayout.setRefreshing(false);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateView() {
        listAdapter.addData(data);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void setData(Datas datas) {
        this.data = datas;
    }

    /**
     * 设置上拉加载
     */
    private void setUpLoad(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleCount = layoutManager.getChildCount();
                int totalCount = layoutManager.getItemCount();
                int lastVisiableItemPos = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                if(visibleCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisiableItemPos >= totalCount - 1
                        && !isLoading){
                    listPresenter.loadMore(data.date);
                }
            }
        });
    }

    /**
     * 设置下拉刷新
     */
    private void setUpRefresh(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listPresenter.getData();
            }
        });
    }
}