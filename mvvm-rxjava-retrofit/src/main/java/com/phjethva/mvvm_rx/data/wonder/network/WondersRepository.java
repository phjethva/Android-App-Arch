package com.phjethva.mvvm_rx.data.wonder.network;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.phjethva.mvvm_rx.data.ApiHelper;
import com.phjethva.mvvm_rx.data.model.WondersResponse;
import com.phjethva.mvvm_rx.utils.ToastUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class WondersRepository {

    private Application app;
    private Context ctx;

    private MutableLiveData<List<WondersResponse.DataWonders>> mutableAllWonders = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public WondersRepository(Application application) {
        this.app = application;
        this.ctx = application.getApplicationContext();
    }

    public MutableLiveData<List<WondersResponse.DataWonders>> fetchAllWonders() {
        WondersRequest service = ApiHelper.getClient().create(WondersRequest.class);
        Observable<WondersResponse> observable = service.getAllWonders();
        disposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(data -> Observable.fromIterable(data.dataWonders))
                        .flatMap(x -> x)
                        .toList()
                        .toObservable()
                        .subscribeWith(new DisposableObserver<List<WondersResponse.DataWonders>>() {
                            @Override
                            public void onNext(List<WondersResponse.DataWonders> dataWonders) {
                                if (dataWonders != null && dataWonders.size() != 0) {
                                    mutableAllWonders.setValue(dataWonders);
                                    Log.d("TAG_API_suc_1", "Data Updated.");
                                    ToastUtils.showToast(ctx, "Data Updated.");
                                } else {
                                    Log.d("TAG_API_suc_0", "No Data Found");
                                    ToastUtils.showToast(ctx, "No Data Found");
                                }
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                Log.d("TAG_API_err_0", throwable.getMessage());
                                ToastUtils.showToast(ctx, "" + throwable.getMessage());
                            }

                            @Override
                            public void onComplete() {
                            }
                        })
        );
        return mutableAllWonders;
    }

    public void dispose() {
        if (disposable != null) {
            disposable.clear();
        }
    }

}
