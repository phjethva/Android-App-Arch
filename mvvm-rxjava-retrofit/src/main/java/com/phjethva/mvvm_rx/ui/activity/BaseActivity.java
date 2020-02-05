package com.phjethva.mvvm_rx.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.phjethva.mvvm_rx.ui.activity.login.LoginActivity;
import com.phjethva.mvvm_rx.ui.activity.main.MainActivity;
import com.phjethva.mvvm_rx.utils.NetworkUtils;
import com.phjethva.mvvm_rx.utils.ToastUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void showToast(String msg) {
        ToastUtils.showToast(getApplicationContext(), msg);
    }

    public void goTOHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goTOLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
