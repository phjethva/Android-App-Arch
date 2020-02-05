package com.phjethva.mvvm_rx.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.phjethva.mvvm_rx.data.model.LoginResponse;

public class SharedPrefUtils {

    private static final String SHARED_PREF = "pref_mvvm_rxjava_retrofit";

    public static int getInt(Context ctx, String key) {
        return ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).getInt(key, 0);
    }

    public static void setInt(Context ctx, String key, int val) {
        ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit().putInt(key, val).apply();
    }

    public static boolean getBoolean(Context ctx, String key) {
        return ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static void setBoolean(Context ctx, String key, boolean val) {
        ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit().putBoolean(key, val).apply();
    }

    public static String getString(Context ctx, String key) {
        return ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).getString(key, null);
    }

    public static void setString(Context ctx, String key, String val) {
        ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit().putString(key, val).apply();
    }

    public static LoginResponse.DataLogin getLogin(Context ctx, String key) {
        Gson gson = new Gson();
        return gson.fromJson(ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).getString(key, ""), LoginResponse.DataLogin.class);
    }

    public static void setLogin(Context ctx, String key, LoginResponse.DataLogin obj) {
        Gson gson = new Gson();
        String val = gson.toJson(obj);
        ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit().putString(key, val).apply();
    }

    public static void clear(Context ctx) {
        ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE).edit().clear().apply();
    }

}
