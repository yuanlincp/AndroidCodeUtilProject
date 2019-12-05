package com.android.yl.androidcodeproject.utils;

import android.widget.Toast;

import com.android.yl.androidcodeproject.base.MyApplication;

/**
 * @author
 * @create 2019/11/12
 * @Describe
 */
public class ToastUtils {

    public static void toast(String message){
        Toast.makeText(MyApplication.getApplication(), message, Toast.LENGTH_SHORT).show();
    }
}
