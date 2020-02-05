package com.phjethva.mvvm_rx.ui.activity.login;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.phjethva.mvvm_rx.R;
import com.phjethva.mvvm_rx.data.ApiCons;
import com.phjethva.mvvm_rx.data.login.LoginCallback;
import com.phjethva.mvvm_rx.databinding.LoginActivityBinding;
import com.phjethva.mvvm_rx.ui.activity.BaseActivity;
import com.phjethva.mvvm_rx.utils.SharedPrefUtils;

public class LoginActivity extends BaseActivity implements LoginCallback {

    private LoginActivityBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        if (SharedPrefUtils.getBoolean(this, ApiCons.IS_LoggedIn)) {
            goTOHome();
        }

        setUp(savedInstanceState);

    }

    private void setUp(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, new LoginViewModelFactory(getApplication(), this)).get(LoginViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        binding.setLoginViewModel(viewModel);
    }

    @Override
    public void onLoginSuccess() {
        goTOHome();
    }

}
