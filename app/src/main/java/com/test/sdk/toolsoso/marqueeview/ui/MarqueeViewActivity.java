package com.test.sdk.toolsoso.marqueeview.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;

import com.test.sdk.toolsoso.R;
import com.test.sdk.toolsoso.bean.MultiTypeBean;
import com.test.sdk.toolsoso.marqueeview.DataUtils;
import com.test.sdk.toolsoso.marqueeview.adapter.HotCommAdapter;
import com.test.sdk.toolsoso.marqueeview.delegate.ImageTextItemViewDelegate;
import com.test.sdk.toolsoso.marqueeview.adapter.LogisticsAdapater;
import com.test.sdk.toolsoso.marqueeview.delegate.MultiTextItemViewDelegate;
import com.test.sdk.toolsoso.marqueeview.bean.Order;
import com.test.sdk.toolsoso.marqueeview.delegate.TextItemViewDelegate;
import com.vs.toolsoso.widget.view.MarqueeView.MarqueeView;
import com.vs.toolsoso.widget.view.MarqueeView.base.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarqueeViewActivity extends AppCompatActivity {

    @BindView(R.id.mv_multi_text)
    MarqueeView mMvMultiText;

    LogisticsAdapater logisticsAdapater;
    HotCommAdapter hotCommAdapter;
    List<Order> logiList = new ArrayList<Order>();
    List<CharSequence> commList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_multi_text);

        ButterKnife.bind(this);

        InitView2();


    }

    private void InitView3(){
        //热门跑马灯
        SpannableString ss1 = new SpannableString("[红包]商城向你转账9.00元");
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        commList.add(ss1);

        SpannableString ss2 = new SpannableString("[/火热]秋季大酬宾");
        Drawable d = getResources().getDrawable(R.mipmap.ic_launcher);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        //用这个drawable对象代替字符串火热
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss2.setSpan(span, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        commList.add(ss2);

        commList.add("The record shows i took the blows");
        commList.add("习近平对全国道德模范表彰活动作出重要指示");
        hotCommAdapter = new HotCommAdapter(this,commList);
        mMvMultiText.setAdapter(hotCommAdapter);
    }

    private void InitView2(){

        int[] statusArr = new int[]{Order.STATUS_CANCEL,Order.STATUS_COMPLETE,Order.STATUS_WAITPAY,Order.STATUS_WAITSEND
                ,Order.STATUS_INTRANSIT ,Order.STATUS_WAITTAKE,Order.STATUS_WAITAPPRAISE,Order.STATUS_WAITBACK,Order.STATUS_ONBACK};
        for (int i = 0; i < statusArr.length; i++) {
            Order order = new Order();
            order.setNewest_date("9月11日");
            order.setOrderid(i);
            order.setNewest_message("第八代英特尔酷睿™i7-856U处理器");
            order.setStatus(statusArr[i]);
            order.setPicUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=23719042,612560189&fm=26&gp=0.jpg");
            if (i==2){
                order.setPicUrl("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2341762344,23347985&fm=26&gp=0.jpg");
            }
            logiList.add(order);
        }
        logisticsAdapater = new LogisticsAdapater(this,logiList);
//        logisticsAdapater.setOnItemClickListener(onItemClickListener);
        mMvMultiText.setAdapter(logisticsAdapater);

    }

    private void InitView() {

        ArrayList<MultiTypeBean> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String title = DataUtils.produceTitle(i);
            int imageResId = DataUtils.produceImageResId(i);
            String content = DataUtils.produceContent(i);
            MultiTypeBean multiTypeBean = new MultiTypeBean();
            multiTypeBean.title = title;
            multiTypeBean.resImageId = imageResId;
            multiTypeBean.content = content;
            int result = i % 3;
            if (result == 0) {
                multiTypeBean.mItemViewType = MultiTypeBean.ItemViewType.text;
            } else if (result == 1) {
                multiTypeBean.mItemViewType = MultiTypeBean.ItemViewType.imageText;
            } else {
                multiTypeBean.mItemViewType = MultiTypeBean.ItemViewType.multiTextAndImage;
            }
            datas.add(multiTypeBean);

        }
        setAdapter(mMvMultiText, datas);


        handleMarqueeView( mMvMultiText, true);

    }


    private void setAdapter(final MarqueeView marqueeView, ArrayList<MultiTypeBean> datas) {

        MultiItemTypeAdapter<MultiTypeBean> multiItemTypeAdapter = new MultiItemTypeAdapter<MultiTypeBean>(this, datas);
        multiItemTypeAdapter.addItemViewDelegate(new TextItemViewDelegate());
        multiItemTypeAdapter.addItemViewDelegate(new ImageTextItemViewDelegate(this));
        multiItemTypeAdapter.addItemViewDelegate(new MultiTextItemViewDelegate());
        multiItemTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Log.e("TAG", "onItemClick: position = " + position);
                if (marqueeView.isStart()) {
                    marqueeView.stopFilp();
                } else {
                    marqueeView.startFlip();
                }
            }
        });
        marqueeView.setAdapter(multiItemTypeAdapter);
    }

    private void handleMarqueeView(MarqueeView marqueeView, boolean isVisibleToUser) {
        if (marqueeView == null) {
            return;
        }
        if(isVisibleToUser){
            marqueeView.startFlip();
        }else{
            marqueeView.stopFilp();
        }
    }

}
