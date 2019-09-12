/*
 * 文件：GlideConfig.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年08月29日 15:06:08
 * 上次修改时间：2019年08月29日 15:06:07
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.test.sdk.toolsoso.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.test.sdk.toolsoso.R;


public class GlideConfig {

    public static final int CIRCLE = 30;//圆形
    public static final int ANGLE = 10;//圆角

    /**
     * 圆型配置
     * @return
     */
    public static RequestOptions CircleConfig(){
        return new RequestOptions()
                .placeholder(R.mipmap.head_error)//加载中占位 R.mipmap.head_error
                .error(R.mipmap.head_error)//加载失败 R.mipmap.head_error
                .fallback(R.mipmap.ic_launcher) // R.mipmap.ic_launcher
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)//磁盘缓存
                .skipMemoryCache(true);//不做内存缓存;
    }


    /**
     * 圆角配置
     * @return
     */
    public static RequestOptions AngleConfig(){
        return new RequestOptions()
                .placeholder(R.mipmap.pic_loading)//加载中占位
                .error(R.mipmap.pic_loading)//加载失败 默认 img_load_fail
                .fallback(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true);//不做内存缓存;
//                .centerCrop();//填充到imageview 设置的宽高 加了就没有圆角圆形效果
    }


    /**
     * 图片背景
     * @return
     */
    public static RequestOptions BackgroudConfig(){
        return new RequestOptions()
                .placeholder(R.mipmap.pic_loading)//加载中占位
                .error(R.mipmap.pic_loading)//加载失败
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .centerCrop();//填充到imageview 设置的宽高 加了就没有圆角圆形效果
    }
}
