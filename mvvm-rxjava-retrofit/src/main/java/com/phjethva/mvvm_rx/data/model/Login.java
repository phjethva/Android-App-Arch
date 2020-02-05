package com.phjethva.mvvm_rx.data.model;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;

public class Login extends BaseObservable {

    private String email;
    private String password;

    public Login() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidEmail() {
        return !TextUtils.isEmpty(getEmail()) &&
                Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }

    public boolean isValidPassword() {
        return !TextUtils.isEmpty(getPassword()) &&
                getPassword().length() > 5;
    }

}
