package com.faruk.android.mvvm_sample.di;

import com.faruk.android.mvvm_sample.view.ui.DataListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract DataListFragment contributeDataListFragment();
}
