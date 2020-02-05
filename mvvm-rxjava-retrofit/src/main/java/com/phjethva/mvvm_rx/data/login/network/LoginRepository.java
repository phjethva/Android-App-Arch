package com.phjethva.mvvm_rx.data.login.network;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.phjethva.mvvm_rx.data.ApiCons;
import com.phjethva.mvvm_rx.data.ApiHelper;
import com.phjethva.mvvm_rx.data.login.LoginCallback;
import com.phjethva.mvvm_rx.data.model.Login;
import com.phjethva.mvvm_rx.data.model.LoginResponse;
import com.phjethva.mvvm_rx.utils.SharedPrefUtils;
import com.phjethva.mvvm_rx.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginRepository {

    private Application app;
    private Context ctx;
    private LoginCallback loginCallback;

    private MutableLiveData<LoginResponse.DataLogin> mutableLogin = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public LoginRepository(Application application, LoginCallback loginCallback) {
        this.app = application;
        this.ctx = application.getApplicationContext();
        this.loginCallback = loginCallback;
    }

    public MutableLiveData<LoginResponse.DataLogin> fetchLogin(Login login) {
        LoginRequest service = ApiHelper.getClient().create(LoginRequest.class);
        Map<String, RequestBody> map = new HashMap<>();
        map.put(ApiCons.KEY_Email, RequestBody.create(MediaType.parse("text/plain"), login.getEmail()));
        map.put(ApiCons.KEY_Password, RequestBody.create(MediaType.parse("text/plain"), login.getPassword()));
        Observable<LoginResponse> observable = service.getLogin(map);
        disposable.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<LoginResponse>() {
                            @Override
                            public void onNext(LoginResponse response) {
                                if (response.dataLogin != null) {
                                    mutableLogin.setValue(response.dataLogin);
                                    SharedPrefUtils.setBoolean(ctx, ApiCons.IS_LoggedIn, true);
                                    SharedPrefUtils.setLogin(ctx, ApiCons.User_Info, response.dataLogin);
                                    Log.d("TAG_API_suc_1", "Login Success.");
                                    ToastUtils.showToast(ctx, "Login Success.");
                                    loginCallback.onLoginSuccess();
                                } else {
                                    Log.d("TAG_API_suc_0", response.message);
                                    ToastUtils.showToast(ctx, response.message);
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
        return mutableLogin;
    }

    public void dispose() {
        if (disposable != null) {
            disposable.clear();
        }
    }

}
