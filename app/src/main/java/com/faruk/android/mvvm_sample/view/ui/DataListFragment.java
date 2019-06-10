package com.faruk.android.mvvm_sample.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.faruk.android.mvvm_sample.R;
import com.faruk.android.mvvm_sample.databinding.FragmentListBinding;
import com.faruk.android.mvvm_sample.di.Injectable;
import com.faruk.android.mvvm_sample.service.model.Data;
import com.faruk.android.mvvm_sample.view.adapter.DataListAdapter;
import com.faruk.android.mvvm_sample.view.callback.ItemClickCallback;
import com.faruk.android.mvvm_sample.viewmodel.DataListViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class DataListFragment extends Fragment implements Injectable {
    public static final String TAG = "DataListFragment";
    private DataListAdapter dataListAdapter;
    private FragmentListBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);

        dataListAdapter = new DataListAdapter(itemClickCallback);
        binding.dataList.setAdapter(dataListAdapter);
        binding.setIsLoading(true);

        return (View) binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final DataListViewModel viewModel = ViewModelProviders.of(this,
                viewModelFactory).get(DataListViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(DataListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getDataListObservable().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(@Nullable List<Data> projects) {
                if (projects != null) {
                    binding.setIsLoading(false);
                    dataListAdapter.setDataList(projects);
                }
            }
        });
    }

    private final ItemClickCallback itemClickCallback = new ItemClickCallback() {
        @Override
        public void onClick(Data project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {

                ((MainActivity) getActivity()).show(project);
            }
        }
    };
}
