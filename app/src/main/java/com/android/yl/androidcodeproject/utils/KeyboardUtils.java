package com.android.yl.androidcodeproject.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author YuanLin
 * @create 2019/8/14
 * @Describe 软键盘工具类
 */
public class KeyboardUtils {
    /**
     * 自动弹软键盘
     *
     * @param context
     * @param et
     */
    public static void showSoftInput(final Context context, EditText et) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity) context).runOnUiThread(() -> {
                    et.setFocusable(true);
                    et.setFocusableInTouchMode(true);
                    //请求获得焦点
                    et.requestFocus();
                    //调用系统输入法
                    InputMethodManager inputManager = (InputMethodManager) et
                            .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.showSoftInput(et, 0);
                });
            }
        }, 200);
    }


    /**
     * 自动关闭软键盘
     *
     * @param activity
     */
    public static void closeKeybord(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    /**
     * 关闭软键盘
     *
     * @param activity
     * @param view
     */
    public static void closeKeyBoard(Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 打开/关闭  相互切换
     *
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}
