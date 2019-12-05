package com.android.yl.androidcodeproject.utils;

import android.util.Log;

import androidx.multidex.BuildConfig;


/**
 * @author
 * @create 2019/7/30
 * @Describe
 */
public class LogUtils {

    public static void i(String message) {
        if (BuildConfig.DEBUG) {
            int segmentSize = 3 * 1024;
            long length = message.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.i("info", "" + message);
            } else {
                while (message.length() > segmentSize) {// 循环分段打印日志
                    String logContent = message.substring(0, segmentSize);
                    message = message.replace(logContent, "");
                    Log.i("info", "" + logContent);
                }
                Log.i("info", "" + message);// 打印剩余日志
            }
        }
    }
}
