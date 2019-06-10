package com.faruk.android.mvvm_sample.di;

import com.faruk.android.mvvm_sample.service.network.DataApi;
import com.faruk.android.mvvm_sample.viewmodel.DataViewModelFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
class AppModule {
    @Singleton @Provides
    DataApi provideGithubService() {
        return new Retrofit.Builder()
                .baseUrl(DataApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DataApi.class);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new DataViewModelFactory(viewModelSubComponent.build());
    }
}
