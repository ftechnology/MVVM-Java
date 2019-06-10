package com.faruk.android.mvvm_sample.service.network;

import com.faruk.android.mvvm_sample.service.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataApi {
    String BASE_URL = "https://rfit-2015.000webhostapp.com/TestData/";

    @GET("response.json")
    Call<List<Data>> getDataList();
}
