package com.phjethva.rx.ui.activity.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.phjethva.rx.R;
import com.phjethva.rx.data.ApiHelper;
import com.phjethva.rx.data.model.WondersResponse;
import com.phjethva.rx.data.wonder.network.WondersRequest;
import com.phjethva.rx.ui.activity.BaseActivity;
import com.phjethva.rx.ui.adapter.WondersAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements WondersAdapter.WondersAdapterItemClick {

    private List<WondersResponse.DataWonders> listWonders = new ArrayList<>();
    private WondersAdapter wondersAdapter;
    private RecyclerView rvWonders;
    private SwipeRefreshLayout srlWonders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvWonders = findViewById(R.id.rv_wonders);
        srlWonders = findViewById(R.id.srl_wonders);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvWonders.setLayoutManager(layoutManager);
        wondersAdapter = new WondersAdapter(this, listWonders);
        rvWonders.setAdapter(wondersAdapter);

        srlWonders.setColorSchemeResources(R.color.colorAccent);
        srlWonders.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isNetworkConnected()) {
                    getAllWonders();
                } else {
                    showToast(getString(R.string.no_network));
                }
                srlWonders.setRefreshing(false);
            }
        });


        if (isNetworkConnected()) {
            getAllWonders();
        } else {
            showToast(getString(R.string.no_network));
        }

    }

    private void getAllWonders() {
        WondersRequest service = ApiHelper.getClient().create(WondersRequest.class);
        Observable<WondersResponse> observable = service.getAllWonders();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(data -> Observable.fromIterable(data.dataWonders))
                .flatMap(x -> x)
                .toList()
                .toObservable()
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(List<WondersResponse.DataWonders> dataWonders) {
        if (dataWonders != null && dataWonders.size() != 0) {
            listWonders = dataWonders;
            wondersAdapter.notifyData(listWonders);
            showToast("Data Updated.");
        } else {
            showToast("No Data Found");
        }
    }

    private void handleError(Throwable throwable) {
        Log.d("TAG_API_err", throwable.getMessage());
        Toast.makeText(getApplicationContext(), "Error In Result.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void callbackWondersAdapterItemClick(int position) {
        showToast(listWonders.get(position).name + " removed.");
        listWonders.remove(position);
        wondersAdapter.notifyData(listWonders);
    }

}
