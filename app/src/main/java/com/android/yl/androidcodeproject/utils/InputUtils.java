package com.android.yl.androidcodeproject.utils;

import android.text.InputFilter;
import android.text.Spanned;


import com.android.yl.androidcodeproject.R;
import com.android.yl.androidcodeproject.base.MyApplication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 不允许输入表情
 * @create 2019/10/16
 * @Describe
 */
public class InputUtils {

    private static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
    //不允许输入表情
    public static InputFilter inputFilter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                ToastUtils.toast(MyApplication.getApplication().getResources().getString(R.string.toast_expression));
                return "";
            }
            return null;
        }
    };
}
