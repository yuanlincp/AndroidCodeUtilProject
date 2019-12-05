package com.android.yl.androidcodeproject.http;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.android.yl.androidcodeproject.R;


/**
 * @author YuanLin
 * @description 显示加载等待时的dialog
 * @time 2019/7/18
 */
public class HttpLoadDialogUtils {
    public String TAG = getClass().getSimpleName();

    private boolean isShow = true;
    private static AlertDialog.Builder builder;
    private static AlertDialog dialog;


    public static void showDialog(Context context) {
        if (dialog != null && dialog.isShowing()) {
            return;
        }
        if (dialog == null) {
            builder = new AlertDialog.Builder(context);
            View view = View.inflate(context, R.layout.dialog_show_loading, null);
            builder.setView(view);
            dialog = builder.create();
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(dialog1 -> {
            dialog.dismiss();
            dialog = null;
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(context.getResources().getColor(android.R.color.transparent)));
        dialog.show();

    }

    public static void hidDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
