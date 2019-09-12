/*
 * 文件：HotCommAdapter.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年09月11日 10:24:52
 * 上次修改时间：2019年09月11日 10:24:52
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.test.sdk.toolsoso.marqueeview.adapter;

import android.content.Context;
import android.widget.TextView;

import com.test.sdk.toolsoso.R;
import com.vs.toolsoso.widget.view.MarqueeView.base.CommonAdapter;
import com.vs.toolsoso.widget.view.MarqueeView.base.ViewHolder;

import java.util.List;

public class HotCommAdapter extends CommonAdapter<CharSequence> {


    public HotCommAdapter(Context context, List<CharSequence> datas) {
        super(context, R.layout.item_hot_comm, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, CharSequence item, int position) {
        TextView tvComm = viewHolder.getView(R.id.hotComm);
        tvComm.setText(item);
    }
}
