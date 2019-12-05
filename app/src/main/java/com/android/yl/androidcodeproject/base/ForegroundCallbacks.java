package com.android.yl.androidcodeproject.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * @author
 * @create 2019/8/22
 * @Describe 监听app前台和后台的切换
 */
public class ForegroundCallbacks implements Application.ActivityLifecycleCallbacks {
    private int activityAount = 0;
    private boolean isAppForeground = true;//是否处于前台运行


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        activityAount++;
        if (activityAount > 0) {
            if (!isAppForeground) {//从后台变前台运行

            }
            isAppForeground = true;

            // do something by yourself

        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        activityAount--;
        if (activityAount == 0) {
            isAppForeground = false;

        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
//        LogUtils.i("onActivityDestroyed");
    }
}
