package com.faruk.android.mvvm_sample.view.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.faruk.android.mvvm_sample.R;
import com.faruk.android.mvvm_sample.databinding.ListItemBinding;
import com.faruk.android.mvvm_sample.service.model.Data;
import com.faruk.android.mvvm_sample.view.callback.ItemClickCallback;

import java.util.List;
import java.util.Objects;

public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.DataViewHolder> {

    List<? extends Data> dataList;

    @Nullable
    private final ItemClickCallback itemClickCallback;

    public DataListAdapter(@Nullable ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public void setDataList(final List<? extends Data> dataList) {
        if (this.dataList == null) {
            this.dataList = dataList;
            notifyItemRangeInserted(0, dataList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return DataListAdapter.this.dataList.size();
                }

                @Override
                public int getNewListSize() {
                    return dataList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return DataListAdapter.this.dataList.get(oldItemPosition).id ==
                            dataList.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Data project = dataList.get(newItemPosition);
                    Data old = dataList.get(oldItemPosition);
                    return project.id == old.id
                            && Objects.equals(project.title, old.title);
                }
            });
            this.dataList = dataList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item,
                        parent, false);

        binding.setCallback(itemClickCallback);

        return new DataViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.binding.setData(dataList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    static class DataViewHolder extends RecyclerView.ViewHolder {

        final ListItemBinding binding;

        public DataViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
