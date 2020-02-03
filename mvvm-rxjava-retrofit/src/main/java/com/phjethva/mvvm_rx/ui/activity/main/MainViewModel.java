package com.phjethva.mvvm_rx.ui.activity.main;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phjethva.mvvm_rx.R;
import com.phjethva.mvvm_rx.data.model.WondersResponse;
import com.phjethva.mvvm_rx.data.wonder.network.WondersRepository;
import com.phjethva.mvvm_rx.ui.adapter.WondersAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private Context ctx;
    private WondersRepository wondersRepository;
    private MutableLiveData<List<WondersResponse.DataWonders>> mutableAllWonders;
    private WondersAdapter wondersAdapter;

    public MainViewModel(Application application) {
        super(application);
        this.ctx = application.getApplicationContext();
        this.wondersRepository = new WondersRepository(application);
    }

    public void init() {
        wondersAdapter = new WondersAdapter(R.layout.item_rv_wonders, this);
        mutableAllWonders = new MutableLiveData<>();
    }

    public WondersAdapter getWondersAdapter() {
        return wondersAdapter;
    }

    public WondersResponse.DataWonders getWonder(int position) {
        return mutableAllWonders.getValue().get(position);
    }

    public void setWondersInAdapter(List<WondersResponse.DataWonders> dataWonders) {
        this.wondersAdapter.setAllWonders(dataWonders);
        this.wondersAdapter.notifyDataSetChanged();
    }

    public MutableLiveData<List<WondersResponse.DataWonders>> getMutableAllWonders() {
        mutableAllWonders = wondersRepository.fetchAllWonders();
        return mutableAllWonders;
    }

    public void onItemClickListen(int position) {
        List<WondersResponse.DataWonders> list = mutableAllWonders.getValue();
        list.remove(position);
        mutableAllWonders.setValue(list);
    }

    @BindingAdapter("setAdapter")
    public static void bindRecyclerViewAdapter(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .resize(1920, 1080)
                .centerCrop()
                .into(imageView);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        wondersRepository.dispose();
    }

}
