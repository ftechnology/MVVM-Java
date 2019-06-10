package com.faruk.android.mvvm_sample.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.faruk.android.mvvm_sample.service.model.Data;
import com.faruk.android.mvvm_sample.service.network.DataList;

import java.util.List;

import javax.inject.Inject;

public class DataListViewModel extends AndroidViewModel {
    private final LiveData<List<Data>> dataListObservable;

    @Inject
    public DataListViewModel(@NonNull DataList dataList, @NonNull Application application) {
        super(application);

        // If any transformation is needed, this can be simply done by Transformations class ...
        dataListObservable = dataList.getDataList();
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Data>> getDataListObservable() {
        return dataListObservable;
    }
}
