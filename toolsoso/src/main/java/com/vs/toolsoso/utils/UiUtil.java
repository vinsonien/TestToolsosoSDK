package com.vs.toolsoso.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.text.method.PasswordTransformationMethod;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import static com.vs.toolsoso.utils.ToolUtils.dip2px;

/**
 * @author: S
 * @date: 2019/5/15 11:51
 * @description:
 */
public class UiUtil {

    /**
     * EditText 使用密码方式显示
     *
     * @param ed
     */
    public static void EditTextToPwd(EditText ed) {
        ed.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    /**
     * EditText 使用s手机号码
     *
     * @param ed
     */
    public static void EditTextToPhone(EditText ed) {
        ed.setInputType(InputType.TYPE_CLASS_PHONE);
    }

    /**
     * EditText 使用数字
     *
     * @param ed
     */
    public static void EditTextNum(EditText ed){
        ed.setInputType(InputType.TYPE_CLASS_NUMBER);
    }


    /**
     * EditText 密文
     *
     * @param ed
     */
    public static void EditForPwd(EditText ed){
        ed.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }


    /**
     * EditText 限制输入字符集
     *
     * @param ed
     */
    public static void EditTextKeyListener(EditText ed,String digits){
        ed.setKeyListener(DigitsKeyListener.getInstance(digits));
    }

    /**
     * 设置edittext 默认提示文字
     * @param ed
     * @param h
     */
    public static void SetEdHint(EditText ed,String h){
        ed.setHint(h);
    }

    /**
     * 设置edittext 输入长度
     * @param ed
     * @param size
     */
    public static void EditTextSize(EditText ed,int size){
        ed.setFilters(new InputFilter[]{new InputFilter.LengthFilter(size)});
    }

    //
    /**
     * EditText 限制小数点后两位
     *
     */
    public static void setPricePoint(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

        });

    }

    /**
     * 设置TextView 左边图标
     * @param v
     * @param drawable
     * @param padding
     * @param c
     */
    public static void TextDrawableLeft(TextView v, Drawable drawable,
                                        int padding, Context c) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        v.setCompoundDrawables(drawable, null, null, null);
        v.setCompoundDrawablePadding(dip2px(c, padding));
    }

    /**
     * 设置TextView 右边图标
     * @param v
     * @param drawable
     * @param padding
     * @param c
     */
    public static void TextDrawableRight(TextView v, Drawable drawable,
                                         int padding, Context c) {
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        v.setCompoundDrawables(null, null, drawable, null);
        v.setCompoundDrawablePadding(dip2px(c, padding));
    }


    /**
     * EditText获取焦点并显示软键盘
     */
    public static void ShowSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }


    /**
     * 初始化 SwipeRefreshLayout
     * @param mContext
     * @param swipeLayout
     * @param color 颜色
     * @param     distance 距离 dp   如：50
     */
    public static void InitSwipeRefreshLayout(Context mContext, SwipeRefreshLayout swipeLayout,
                                              int color, int distance){
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeLayout.setColorSchemeResources(color);
        swipeLayout.setProgressViewOffset(false, 0, ToolUtils.dip2px(mContext, distance));
    }
}
