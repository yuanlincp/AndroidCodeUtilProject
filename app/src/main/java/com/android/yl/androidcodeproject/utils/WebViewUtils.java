package com.android.yl.androidcodeproject.utils;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author YuanLin
 * @create 2019/7/25
 * @Describe
 */
public class WebViewUtils {

    public static void initWebView(WebView webView, OnPageFinishListener onPageFinishListener) {
        WebSettings settings = webView.getSettings();
        // 设置WebView支持JavaScript
        settings.setJavaScriptEnabled(true);
        //支持自动适配
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //是否支持放大缩小
        settings.setSupportZoom(false);
        //是否显示缩放按钮
        settings.setBuiltInZoomControls(false);
        // 把图片加载放在最后来加载渲染
        settings.setBlockNetworkImage(true);
        // 是否允许访问文件
        settings.setAllowFileAccess(false);
        settings.setSaveFormData(true);
//        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        // 支持通过JS打开新窗口
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        //把html中的内容放大webview等宽的一列中
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //设置不让其跳转浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                imgReset(webView);
                if (onPageFinishListener != null) {
                    onPageFinishListener.onPageFinish();
                }
            }
        });
        //不加这个图片显示不出来
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.getSettings().setBlockNetworkImage(false);
    }

    /**
     * 对图片进行重置大小，宽度就是手机屏幕宽度，高度根据宽度比便自动缩放
     **/
    private static void imgReset(WebView webView) {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('img'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var img = objs[i];   " +
                "    img.style.maxWidth = '100%'; " +
                "}" +
                "})()");//img.style.height = 'auto';
    }

    public interface OnPageFinishListener {
        void onPageFinish();
    }
}
