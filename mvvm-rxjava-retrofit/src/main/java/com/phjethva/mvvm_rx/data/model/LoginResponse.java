package com.phjethva.mvvm_rx.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    public int status;

    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("data")
    @Expose
    public LoginResponse.DataLogin dataLogin;

    public static class DataLogin {

        @SerializedName("id")
        @Expose
        public int id;

        @SerializedName("email")
        @Expose
        public String email;

        @SerializedName("password")
        @Expose
        public String password;

        @SerializedName("firstname")
        @Expose
        public String firstname;

        @SerializedName("lastname")
        @Expose
        public String lastname;

        @SerializedName("birth_date")
        @Expose
        public String birthdate;

        @SerializedName("gender")
        @Expose
        public String gender;

    }

}
