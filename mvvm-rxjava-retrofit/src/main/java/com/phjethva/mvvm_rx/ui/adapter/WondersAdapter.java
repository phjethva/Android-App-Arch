package com.phjethva.mvvm_rx.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.phjethva.mvvm_rx.BR;
import com.phjethva.mvvm_rx.data.model.WondersResponse;
import com.phjethva.mvvm_rx.databinding.WonderAdapterBinding;
import com.phjethva.mvvm_rx.ui.activity.main.MainViewModel;

import java.util.List;

public class WondersAdapter extends RecyclerView.Adapter<WondersAdapter.MyViewHolder> {

    private Context ctx;
    private int item;
    private List<WondersResponse.DataWonders> list;
    private MainViewModel mainViewModel;

    public WondersAdapter(@LayoutRes int itemRVWonders, MainViewModel mainViewModel) {
        this.item = itemRVWonders;
        this.mainViewModel = mainViewModel;
    }

    private int getLayoutIdForPosition() {
        return item;
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition();
    }

    public void setAllWonders(List<WondersResponse.DataWonders> dataWonders) {
        list = dataWonders;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final WonderAdapterBinding binding;

        public MyViewHolder(WonderAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBinding(int position) {
            this.binding.setVariable(BR.position, position);
            this.binding.setVariable(BR.mainViewModel, mainViewModel);
            this.binding.executePendingBindings();
        }

        public WonderAdapterBinding getBinding() {
            return binding;
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewItem) {
        ctx = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        //WonderAdapterBinding binding = WonderAdapterBinding.inflate(layoutInflater, parent, false);
        //WonderAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv_wonders, parent, false);
        WonderAdapterBinding binding = DataBindingUtil.inflate(layoutInflater, viewItem, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.setBinding(position);
    }

}
