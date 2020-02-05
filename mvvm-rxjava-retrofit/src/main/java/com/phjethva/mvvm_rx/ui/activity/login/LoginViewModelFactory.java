package com.phjethva.mvvm_rx.ui.activity.login;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.phjethva.mvvm_rx.data.login.LoginCallback;

class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application application;
    private LoginCallback loginCallback;

    public LoginViewModelFactory(Application application, LoginCallback loginCallback) {
        this.application = application;
        this.loginCallback = loginCallback;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new LoginViewModel(application, loginCallback);
    }

}
