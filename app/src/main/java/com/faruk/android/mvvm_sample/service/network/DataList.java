package com.faruk.android.mvvm_sample.service.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.faruk.android.mvvm_sample.service.model.Data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class DataList {
    private DataApi dataApi;

    @Inject
    public DataList(DataApi dataApi) {
        this.dataApi = dataApi;
    }

    public LiveData<List<Data>> getDataList() {
        final MutableLiveData<List<Data>> data = new MutableLiveData<>();

        dataApi.getDataList().enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                // TODO better error handling in part #2 ...
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<Data> getData() {
        final MutableLiveData<Data> data = new MutableLiveData<>();
        return data;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
