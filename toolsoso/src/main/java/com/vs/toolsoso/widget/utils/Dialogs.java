package com.vs.toolsoso.widget.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;

/**
 * 对话框相关
 */
public class Dialogs {
    static String TITLE = "系统提示";

    /***
     * 获取一个dialog对象，进行相关操作
     *
     * @param context
     * @return
     */
    public static AlertDialog.Builder getDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        return builder;
    }

    /***
     * 获取一个进度对话框(耗时操作使用)
     *
     * @param context
     * @param message
     * @return
     */
    public static ProgressDialog getWaitDialog(Context context, String message) {
        ProgressDialog waitDialog = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message);
        }
        return waitDialog;
    }

    public static ProgressDialog getProgressBarDialog(Context context, String message) {
        ProgressDialog d = new ProgressDialog(context);
        if (!TextUtils.isEmpty(message)) {
            d.setMessage(message);
        }
        d.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return d;
    }

    public static AlertDialog.Builder getMessageDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setTitle(TITLE);
        builder.setMessage(message);
        builder.setPositiveButton(" 知道了", onClickListener);
        return builder;
    }

    public static AlertDialog.Builder getReturnDialog(final Context context, String message) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setTitle(TITLE);
        builder.setMessage(message);
        builder.setPositiveButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((AppCompatActivity) context).onBackPressed();
            }
        });
        return builder;
    }

    public static AlertDialog.Builder getReturnDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setTitle(TITLE);
        builder.setMessage(message);
        builder.setPositiveButton("返回", onClickListener);
        return builder;
    }

    /**
     * 获取一个信息对话框
     *
     * @param context
     * @param message
     * @return
     */
    public static AlertDialog.Builder getMessageDialog(Context context, String message) {
        return getMessageDialog(context, message, null);
    }

    public static AlertDialog.Builder getSelectDialog(Context context, String title, String[] arrays, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setItems(arrays, onClickListener);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setPositiveButton("取消", null);
        return builder;
    }

    /**
     * 选择对话框
     *
     * @param context
     * @param arrays
     * @param onClickListener
     * @return
     */
    public static AlertDialog.Builder getSelectDialog(Context context, String[] arrays, DialogInterface.OnClickListener onClickListener) {
        return getSelectDialog(context, "", arrays, onClickListener);
    }

    /**
     * 可设置内容、确定监听对话框
     *
     * @param context
     * @param message
     * @param onClickListener
     * @return
     */
    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setTitle(TITLE);
        builder.setMessage(Html.fromHtml(message));
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        return builder;
    }

    /**
     * 可设置内容、两个按钮监听对话框
     *
     * @param context
     * @param message
     * @param onOkClickListener
     * @param onCancelClickListener
     * @return
     */
    public static AlertDialog.Builder getConfirmDialog(Context context, String title,String message, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancelClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onOkClickListener);
        builder.setNegativeButton("取消", onCancelClickListener);
        return builder;
    }

    /**
     * 可设置内容、两个按钮监听对话框
     *
     * @param context
     * @param message
     * @param onOkClickListener
     * @param onCancelClickListener
     * @return
     */
    public static AlertDialog.Builder getConfirmDialog(Context context, String message, DialogInterface.OnClickListener onOkClickListener, DialogInterface.OnClickListener onCancelClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setTitle(TITLE);
        builder.setMessage(message);
        builder.setPositiveButton("确定", onOkClickListener);
        builder.setNegativeButton("取消", onCancelClickListener);
        return builder;
    }

    /**
     * 可设置标题、内容、按钮文字，监听的对话框
     *
     * @param context
     * @param title                 标题
     * @param message               消息
     * @param okString              OK 按钮
     * @param cancelString          取消按钮
     * @param onOkClickListener
     * @param onCancelClickListener
     * @return
     */
    public static AlertDialog.Builder getConfirmDialog(Context context,
                                                       String title,
                                                       String message,
                                                       String okString,
                                                       String cancelString,
                                                       DialogInterface.OnClickListener onOkClickListener,
                                                       DialogInterface.OnClickListener onCancelClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setPositiveButton(okString, onOkClickListener);
        builder.setNegativeButton(cancelString, onCancelClickListener);
        return builder;
    }

    /**
     * 创建可自定义对话框内容和按钮文字的对话框
     *
     * @param context
     * @param message               对话框内容
     * @param okString              确定按钮文字
     * @param cancelString          取消按钮文字
     * @param onOkClickListener     确定监听
     * @param onCancelClickListener 取消监听
     * @return
     */
    public static AlertDialog.Builder getConfirmDialog(Context context,
                                                       String message,
                                                       String okString,
                                                       String cancelString,
                                                       DialogInterface.OnClickListener onOkClickListener,
                                                       DialogInterface.OnClickListener onCancelClickListener) {
        return getConfirmDialog(context, "", message, okString, cancelString, onOkClickListener, onCancelClickListener);
    }

    public static AlertDialog.Builder getSingleChoiceDialog(Context context, String title, String[] arrays, int selectIndex, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = getDialog(context);
        builder.setSingleChoiceItems(arrays, selectIndex, onClickListener);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setNegativeButton("取消", null);
        return builder;
    }

    /**
     * 单选对话框
     *
     * @param context
     * @param arrays          可供选择的数据
     * @param selectIndex     默认选中的索引
     * @param onClickListener
     * @return
     */
    public static AlertDialog.Builder getSingleChoiceDialog(Context context, String[] arrays, int selectIndex, DialogInterface.OnClickListener onClickListener) {
        return getSingleChoiceDialog(context, "", arrays, selectIndex, onClickListener);
    }
}
