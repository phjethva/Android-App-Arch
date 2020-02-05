package com.phjethva.mvvm_rx.ui.activity.login;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.phjethva.mvvm_rx.data.login.LoginCallback;
import com.phjethva.mvvm_rx.data.login.network.LoginRepository;
import com.phjethva.mvvm_rx.data.model.Login;
import com.phjethva.mvvm_rx.data.model.LoginResponse;
import com.phjethva.mvvm_rx.utils.ToastUtils;

public class LoginViewModel extends AndroidViewModel {

    private Context ctx;
    private Login login;
    private LoginRepository loginRepository;
    private MutableLiveData<LoginResponse.DataLogin> mutableLogin;

    public LoginViewModel(Application application, LoginCallback loginCallback) {
        super(application);
        this.ctx = application.getApplicationContext();
        this.loginRepository = new LoginRepository(application, loginCallback);
    }

    public void init() {
        login = new Login();
        mutableLogin = new MutableLiveData<>();

    }

    public MutableLiveData<LoginResponse.DataLogin> getMutableLogin(Login login) {
        mutableLogin = loginRepository.fetchLogin(login);
        return mutableLogin;
    }

    public TextWatcher emailTVWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                login.setEmail(s.toString());
            }
        };
    }

    public TextWatcher passwordTVWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                login.setPassword(s.toString());
            }
        };
    }

    public void btnLogin() {
        if (!login.isValidEmail()) {
            ToastUtils.showToast(ctx, "Invalid email.");
        } else if (!login.isValidPassword()) {
            ToastUtils.showToast(ctx, "Invalid password.");
        } else {
            getMutableLogin(login);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        loginRepository.dispose();
    }

}
