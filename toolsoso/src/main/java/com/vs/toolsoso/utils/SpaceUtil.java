package com.vs.toolsoso.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * @author: S
 * @date: 2018/12/10 14:22
 * @description:
 */
public class SpaceUtil {

    public static void SetMarginsLinearLayout(View view, int left, int top, int right, int bottom){
        // 1、设置固定大小
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) view.getLayoutParams();
        //设置margin值
        lp.setMargins(isDefaultLeftMargins(lp,left),isDefaultTopMargins(lp,top),
                isDefaultRightMargins(lp,right), isDefaultBottomMargins(lp,bottom));
        view.setLayoutParams(lp);
    }

    public static void SetPadding(View view,int left,int top,int right,int bottom){
        //设置padding值
        view.setPadding(isDefaultLeftPadding(view,left), isDefaultTopPadding(view,top),
                isDefaultRightPadding(view,right), isDefaultBottomPadding(view,bottom));
    }

    public static void SetMarginsRelativeLayout(View view,int left,int top,int right,int bottom){
        // 1、设置固定大小
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
        //设置margin值
        lp.setMargins(isDefaultLeftMargins(lp,left),isDefaultTopMargins(lp,top),
                isDefaultRightMargins(lp,right), isDefaultBottomMargins(lp,bottom));
        view.setLayoutParams(lp);
    }

    public static void SetMarginsFrameLayout(View view,int left,int top,int right,int bottom){
        // 1、设置固定大小
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
        //设置margin值
        lp.setMargins(isDefaultLeftMargins(lp,left),isDefaultTopMargins(lp,top),
                isDefaultRightMargins(lp,right), isDefaultBottomMargins(lp,bottom));
        view.setLayoutParams(lp);
    }

    public static ViewGroup.MarginLayoutParams GetResourcesBuilder(Object defaultObject) {

        if (defaultObject instanceof LinearLayout.LayoutParams) {
            return (LinearLayout.LayoutParams) defaultObject;
        } else if (defaultObject instanceof RelativeLayout.LayoutParams) {
            return (RelativeLayout.LayoutParams) defaultObject;
        } else if (defaultObject instanceof FrameLayout.LayoutParams) {
            return (FrameLayout.LayoutParams) defaultObject;
        }
        return null;
    }


    private static int isDefaultLeftPadding(View view, int left){
        if (left == -1){
            return view.getPaddingLeft();
        }else{
            return left;
        }
    }

    private static int isDefaultRightPadding(View view, int right){
        if (right == -1){
            return view.getPaddingRight();
        }else{
            return right;
        }
    }
    private static int isDefaultTopPadding(View view, int top){
        if (top == -1){
                return view.getPaddingTop();
        }else{
            return top;
        }
    }
    private static int isDefaultBottomPadding(View view, int bottom){
        if (bottom == -1){
            return view.getPaddingBottom();
        }else{
            return bottom;
        }
    }



   private static int isDefaultLeftMargins(ViewGroup.MarginLayoutParams lp, int left){
        if (left == -1){
            return lp.leftMargin;
        }else{
            return left;
        }
   }

    private static int isDefaultRightMargins(ViewGroup.MarginLayoutParams lp,int right){
        if (right == -1){
            return lp.rightMargin;
        }else{
            return right;
        }
    }
    private static int isDefaultTopMargins(ViewGroup.MarginLayoutParams lp,int top){
        if (top == -1){
            return lp.topMargin;
        }else{
            return top;
        }
    }
    private static int isDefaultBottomMargins(ViewGroup.MarginLayoutParams lp,int bottom){
        if (bottom == -1){
            return lp.bottomMargin;
        }else{
            return bottom;
        }
    }

}
