package com.android.yl.androidcodeproject.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.android.yl.androidcodeproject.R;
import com.android.yl.androidcodeproject.utils.StatusUtils;

import butterknife.ButterKnife;


/**
 * @author
 * @description activity 基类
 * @time
 */
public class BaseActivity<T> extends AppCompatActivity {
    private Context mContext;
    public T iPrestener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusUtils.initStatus(this, R.color.color_white);
        MyApplication.getApplication().addActivityToList(this);
        // 设置为竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = BaseActivity.this;
        View rootView = rootView(View.inflate(mContext, R.layout.activity_base, null));
        setContentView(rootView);
        ButterKnife.bind(this);
        iPrestener = getPrestener();
        initView(savedInstanceState);
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getApplication().removeActivityFtomList(this);
    }

    private View rootView(View view) {
        //添加标题布局
        LinearLayout linearLayout = view.findViewById(R.id.llTitle);
        if (linearLayout != null) {
            if (linearLayout.getChildCount() > 0) {
                linearLayout.removeAllViews();
            }
            if (getTitleId() != 0) {
                linearLayout.setVisibility(View.VISIBLE);
                View titleView = View.inflate(mContext, getTitleId(), null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                linearLayout.addView(titleView, params);
            } else {//没有设置标题栏，标题栏隐藏
                linearLayout.setVisibility(View.GONE);
            }
        }
        //添加content布局
        FrameLayout flContent = view.findViewById(R.id.flContent);
        if (flContent != null) {
            if (flContent.getChildCount() > 0) {
                flContent.removeAllViews();
            }
            if (getContentLayoutId() != 0) {
                View contentView = View.inflate(mContext, getContentLayoutId(), null);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                flContent.addView(contentView, params);
            }
        }
        return view;
    }


    //默认使用basetitle
    protected int getTitleId() {
        return 0;
    }

    protected int getContentLayoutId() {
        return 0;
    }

    protected T getPrestener() {
        return null;
    }

    protected void initView(Bundle savedInstanceState) {

    }

    protected void initData() {

    }

    protected void initEvent() {

    }

}
