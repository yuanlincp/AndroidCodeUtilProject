package com.android.yl.androidcodeproject.base;

import android.app.Activity;

import androidx.multidex.MultiDexApplication;

import com.android.yl.androidcodeproject.http.HttpConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;


import java.util.ArrayList;
import java.util.List;


/**
 * @author 尼古拉斯.YL
 * @description myapplication
 * @time 2019/7/19
 */
public class MyApplication extends MultiDexApplication {

    private List<BaseActivity> allActivity;
    private static MyApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        allActivity = new ArrayList<>();
        //application前后台监听
        registerActivityLifecycleCallbacks(new ForegroundCallbacks());
        //smartRefreshLayout的设置
        smartRefreshLayoutSetDefautConfig();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        application = null;
        allActivity.clear();
        allActivity = null;
    }

    public static MyApplication getApplication() {
        return application;
    }

    public void addActivityToList(BaseActivity activity) {
        if (allActivity != null) {
            allActivity.add(activity);
        }
    }

    public void removeActivityFtomList(BaseActivity activity) {
        if (allActivity != null && allActivity.contains(activity)) {
            allActivity.remove(activity);
        }
    }

    public void clearAllActivity() {
        if (allActivity != null) {
            allActivity.clear();
        }
    }

    public void finish() {
        for (Activity a : allActivity) {
            a.finish();
        }
    }

    /**
     * 当前顶部activity
     *
     * @return
     */
    public BaseActivity getTopActivity() {
        return allActivity.get(allActivity.size() - 1);
    }


    /**
     * smartRefreshLayout设置头和脚布局
     */
    private void smartRefreshLayoutSetDefautConfig() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context));
    }


}
