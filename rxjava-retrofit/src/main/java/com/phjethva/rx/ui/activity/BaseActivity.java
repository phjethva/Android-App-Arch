package com.phjethva.rx.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.phjethva.rx.utils.NetworkUtils;
import com.phjethva.rx.utils.ToastUtils;

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

}
