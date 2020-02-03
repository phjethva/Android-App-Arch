package com.phjethva.mvvm_rx.utils;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.phjethva.mvvm_rx.R;

public final class ToastUtils {

    public ToastUtils() {
    }

    public static void showToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        View vwToast = toast.getView();
        vwToast.setBackgroundResource(R.drawable.bg_toast);
        vwToast.setPadding(20, 20, 20, 20);
        TextView tvToast = (TextView) vwToast.findViewById(android.R.id.message);
        tvToast.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        if (Build.VERSION.SDK_INT < 23) {
            tvToast.setTextColor(context.getResources().getColor(R.color.colorWhite));
        } else {
            tvToast.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        }
        toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 50);
        toast.show();
    }

}
