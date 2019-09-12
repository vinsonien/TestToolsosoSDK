/*
 * 文件：GlideWith.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年08月29日 15:09:52
 * 上次修改时间：2019年08月29日 15:09:52
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.test.sdk.toolsoso.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.github.glide_manager.GlideApp;
import com.github.glide_manager.GlideRoundTransform;

public class GlideWith {
    public static void Show(Context context, ImageView view, String url){
        GlideApp.with(context).load(url)
                .apply(GlideConfig.AngleConfig())
                .into(view);
    }

    public static void ShowCircle(Context context, ImageView view, String url){
        ShowFeel(context,GlideConfig.CircleConfig(),view,url,GlideConfig.CIRCLE);
    }

    public static void ShowAngle(Context context,ImageView view,String url){
        ShowFeel(context,GlideConfig.AngleConfig(),view,url,GlideConfig.ANGLE);
    }

    public static void Show(Context context, ImageView view, Drawable drawable){
        GlideApp.with(context).load(drawable)
                .apply(GlideConfig.AngleConfig())
                .into(view);
    }

    public static void ShowCircle(Context context,ImageView view,Drawable drawable){
        ShowFeel(context,GlideConfig.CircleConfig(),view,drawable,GlideConfig.CIRCLE);
    }

    public static void ShowAngle(Context context,ImageView view,Drawable drawable){
        ShowFeel(context,GlideConfig.AngleConfig(),view,drawable,GlideConfig.ANGLE);
    }

    public static void Show(Context context, ImageView view,int id){
        GlideApp.with(context).load(id)
                .apply(GlideConfig.AngleConfig())
                .into(view);
    }

    public static void ShowCircle(Context context,ImageView view,int id){
        ShowFeel(context,GlideConfig.CircleConfig(),view,id,GlideConfig.CIRCLE);
    }

    public static void ShowAngle(Context context,ImageView view,int id){
        ShowFeel(context,GlideConfig.AngleConfig(),view,id,GlideConfig.ANGLE);
    }

    /**
     * 自定义角度
     * @param context
     * @param view
     * @param url
     * @param dp
     */
    private static void ShowFeel(Context context, RequestOptions op, ImageView view, String url, int dp){
        GlideApp.with(context).load(url)
                .transform(new GlideRoundTransform(context,dp))
                .apply(op)
                .into((ImageView) view);
    }

    /**
     * 自定义角度
     * @param context
     * @param view
     * @param drawable
     * @param dp
     */
    private static void ShowFeel(Context context, RequestOptions op,ImageView view, Drawable drawable, int dp){
        GlideApp.with(context).load(drawable)
                .transform(new GlideRoundTransform(context,dp))
                .apply(op)
                .into((ImageView) view);
    }
    /**
     * 自定义角度
     * @param context
     * @param view
     * @param id
     * @param dp
     */
    private static void ShowFeel(Context context, RequestOptions op,ImageView view, int id, int dp){
        GlideApp.with(context).load(id)
                .transform(new GlideRoundTransform(context,dp))
                .apply(op)
                .into((ImageView) view);
    }
}
