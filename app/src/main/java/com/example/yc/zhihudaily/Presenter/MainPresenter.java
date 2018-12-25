package com.example.yc.zhihudaily.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.yc.zhihudaily.Model.Datas;
import com.example.yc.zhihudaily.Model.NetModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements Contract.ListPresenter{

    Contract.ViewPresenter viewPresenter;
    NetModel netModel = new NetModel();

    public MainPresenter(Contract.ViewPresenter viewPresenter){
        this.viewPresenter = viewPresenter;
    }

    @Override
    public void getData() {
        Call<Datas> call = netModel.refresh();
        call.enqueue(new Callback<Datas>() {
            @Override
            public void onResponse(@NonNull Call<Datas> call, @NonNull Response<Datas> response) {
                viewPresenter.setData(response.body());
                viewPresenter.initView();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable throwable) {
                Toast.makeText((Context) viewPresenter, "Internet connecting failed", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void refreshData() {
        Call<Datas> call = netModel.refresh();
        call.enqueue(new Callback<Datas>() {
            @Override
            public void onResponse(@NonNull Call<Datas> call, @NonNull Response<Datas> response) {
                viewPresenter.setData(response.body());
                viewPresenter.refreshView();
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable throwable) {
                Toast.makeText((Context) viewPresenter, "Internet connecting failed", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void loadMore(String date) {
        Call<Datas> call = netModel.update(date);
        call.enqueue(new Callback<Datas>() {
            @Override
            public void onResponse(@NonNull Call<Datas> call, @NonNull Response<Datas> response) {
                viewPresenter.setData(response.body());
                viewPresenter.updateView();
            }

            @Override
            public void onFailure(@NonNull Call<Datas> call, @NonNull Throwable t) {
                Toast.makeText((Context) viewPresenter, "Internet connecting failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
