package com.vs.toolsoso.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.vs.toolsoso.widget.callback.DialogClickListener;
import com.vs.toolsoso.widget.utils.Dialogs;

/**
 * @author: S
 * @date: 2018/11/9 16:43
 * @description:
 */
public class BaseDialog {

    private Context context;
    private String message = "提示内容";
    private String title = "提示";
    private DialogClickListener listener;
    private AlertDialog.Builder alertDialog;

    public BaseDialog(Context context, DialogClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.alertDialog = Dialogs.getConfirmDialog(context,
                message,onOkClickListener, onCancelClickListener);
    }

    public BaseDialog(Context context, String message, DialogClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.message = message;
        this.alertDialog = Dialogs.getConfirmDialog(context,
                message,onOkClickListener, onCancelClickListener);
    }
    public BaseDialog(Context context, String title,String message, DialogClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.message = message;
        this.title = title;
        this.alertDialog = Dialogs.getConfirmDialog(context,
                message,onOkClickListener, onCancelClickListener);
    }

    public BaseDialog(Context context, AlertDialog.Builder alertDialog, DialogClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.alertDialog = alertDialog;
    }

    DialogInterface.OnClickListener onOkClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (listener != null){
                listener.onCommit();
            }else{
                Log.e("BaseDialog","DialogClickListener is null");
            }
        }
    };

    DialogInterface.OnClickListener onCancelClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (listener != null){
                listener.onCancel();
            }else{
                Log.e("BaseDialog","DialogClickListener is null");
            }
        }
    };

    public void create(){
        if (alertDialog != null){
            alertDialog.create();
        }else{
            Log.e("BaseDialog","create fail because of AlertDialog.Builder is null");
        }
    }

    public void show(){
        if (alertDialog != null){
            alertDialog.show();
        }else{
            Log.e("BaseDialog","show fail because of AlertDialog.Builder is null");
        }
    }
}
