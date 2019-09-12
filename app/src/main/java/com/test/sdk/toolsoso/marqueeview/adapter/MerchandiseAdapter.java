/*
 * 文件：MerchandiseAdapter.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年09月03日 17:18:35
 * 上次修改时间：2019年09月03日 17:18:35
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.test.sdk.toolsoso.marqueeview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.sdk.toolsoso.R;
import com.test.sdk.toolsoso.glide.GlideWith;
import com.test.sdk.toolsoso.marqueeview.bean.Merchandise;
import com.vs.toolsoso.widget.view.MarqueeView.MarqueeView;

import java.util.ArrayList;
import java.util.List;


public class MerchandiseAdapter extends BaseQuickAdapter<Merchandise, BaseViewHolder> {

    Context context;

    public MerchandiseAdapter(Context c) {
        super(R.layout.item_merchandise);
        this.context = c;
    }

    @Override
    protected void convert(BaseViewHolder helper, Merchandise item) {


        String url = "http://img3.duitang.com/uploads/item/201504/02/20150402H2753_JuTG3.thumb.700_0.jpeg";
        GlideWith.Show(context,helper.getView(R.id.merPic),url);
        helper.setText(R.id.merName,"上看看扩扩扩军扩军扩军扩军扩");
        helper.setText(R.id.merPrice,"¥" + 999);
        helper.setText(R.id.payAmount,"1024人付款");
        LinearLayout tagLayout = helper.getView(R.id.merTagLayout);
        tagLayout.removeAllViews();
        for (int i = 0; i < 2; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,6,0);
            TextView textView = new TextView(context);
            textView.setLayoutParams(params);
            textView.setPadding(10,4,10,4);
            textView.setTextSize(10);
            textView.setTextColor(Color.GRAY);
            textView.setText("标签");
            tagLayout.addView(textView);
        }


        List<CharSequence> commList = new ArrayList<>();
        MarqueeView marqueeView = helper.getView(R.id.marqueeView);//跑马灯
        HotCommAdapter hotCommAdapter;

        //热门跑马灯
        SpannableString ss1 = new SpannableString("[红包]商城向你转账9.00元");
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        commList.add(ss1);

        SpannableString ss2 = new SpannableString("[/火热]秋季大酬宾");
        Drawable d = context.getResources().getDrawable(R.mipmap.icon_close);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        //用这个drawable对象代替字符串火热
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss2.setSpan(span, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        commList.add(ss2);

        commList.add("The record shows i took the blows");
        commList.add("习近平对全国道德模范表彰活动作出重要指示");
        hotCommAdapter = new HotCommAdapter(context,commList);
        marqueeView.setAdapter(hotCommAdapter);

    }
}
