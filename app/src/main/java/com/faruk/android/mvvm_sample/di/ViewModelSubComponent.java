package com.faruk.android.mvvm_sample.di;

import com.faruk.android.mvvm_sample.viewmodel.DataListViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link com.faruk.android.mvvm_sample.viewmodel.DataViewModelFactory}.
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    DataListViewModel dataListViewModel();
}
