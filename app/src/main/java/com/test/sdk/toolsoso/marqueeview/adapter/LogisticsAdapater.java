/*
 * 文件：LogisticsAdapater.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年09月11日 09:30:19
 * 上次修改时间：2019年09月11日 09:30:19
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.test.sdk.toolsoso.marqueeview.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.glide_manager.GlideApp;
import com.github.glide_manager.GlideRoundTransform;
import com.test.sdk.toolsoso.R;
import com.test.sdk.toolsoso.glide.GlideConfig;
import com.test.sdk.toolsoso.marqueeview.bean.Order;
import com.vs.toolsoso.widget.view.MarqueeView.base.CommonAdapter;
import com.vs.toolsoso.widget.view.MarqueeView.base.ViewHolder;

import java.util.List;

public class LogisticsAdapater extends CommonAdapter<Order> {

    Context context;

    public LogisticsAdapater(Context context, List<Order> datas) {
        super(context, R.layout.item_logistics, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder viewHolder, Order item, int position) {
        TextView dateTv = viewHolder.getView(R.id.dateLogistics);
        TextView statusTv = viewHolder.getView(R.id.statusLogistics);
        TextView newestmessageTv = viewHolder.getView(R.id.newestmessageLogistics);
        ImageView picImv = viewHolder.getView(R.id.picUrlLogistics);

        dateTv.setText(item.getNewest_date());
        statusTv.setText(item.getStatus()+ "");
        newestmessageTv.setText(item.getNewest_message());


        RequestOptions op = new RequestOptions()
                .placeholder(picImv.getDrawable())//加载中占位 取上一次加载好的图
                .error(R.mipmap.pic_loading)//加载失败 默认 img_load_fail
                .fallback(R.mipmap.ic_launcher)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true);

        GlideApp.with(context).load(item.getPicUrl())
                .transform(new GlideRoundTransform(context, GlideConfig.ANGLE))
                .apply(op)
                .into(picImv);

//        GlideWith.ShowAngle(context,picImv,item.getPicUrl());
    }
}
