package com.test.sdk.toolsoso.marqueeview.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.sdk.toolsoso.R;
import com.test.sdk.toolsoso.bean.MultiTypeBean;
import com.test.sdk.toolsoso.marqueeview.DataUtils;
import com.test.sdk.toolsoso.marqueeview.adapter.HotCommAdapter;
import com.test.sdk.toolsoso.marqueeview.adapter.LogisticsAdapater;
import com.test.sdk.toolsoso.marqueeview.adapter.MerchandiseAdapter;
import com.test.sdk.toolsoso.marqueeview.bean.Merchandise;
import com.test.sdk.toolsoso.marqueeview.bean.Order;
import com.test.sdk.toolsoso.marqueeview.delegate.ImageTextItemViewDelegate;
import com.test.sdk.toolsoso.marqueeview.delegate.MultiTextItemViewDelegate;
import com.test.sdk.toolsoso.marqueeview.delegate.TextItemViewDelegate;
import com.vs.toolsoso.widget.view.LoadingErrorBgLayoutGroupView;
import com.vs.toolsoso.widget.view.MarqueeView.MarqueeView;
import com.vs.toolsoso.widget.view.MarqueeView.base.MultiItemTypeAdapter;
import com.vs.toolsoso.widget.view.RefreshRecyclerGroupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarqueeAdapterAcitivity extends AppCompatActivity {

    @BindView(R.id.loadlayout)
    LoadingErrorBgLayoutGroupView loadlayout;
    @BindView(R.id.recygroup)
    RefreshRecyclerGroupView recygroup;
    RecyclerView recyclerView;
    SwipeRefreshLayout refresh;
    MerchandiseAdapter adapter;
    List<Merchandise> list = new ArrayList<Merchandise>();
    boolean isRefresh = false;
    List<CharSequence> commList = new ArrayList<>();
    MarqueeView marqueeView;//跑马灯
    HotCommAdapter hotCommAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);

        ButterKnife.bind(this);

        InitView();
        getData();

    }

    private void InitView() {
        recyclerView = recygroup.recy;
        refresh = recygroup.swipe;

        recyclerView = recygroup.recy;
        refresh = recygroup.swipe;

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
//        recyclerView.addItemDecoration(new SpaceItemDecoration(30));
        recyclerView.setBackgroundColor(Color.WHITE);
        loadlayout.bindMainView(recygroup);//绑定加载布局
        loadlayout.setOnErrorLoadListener(onErrorLoadListener);
        adapter = new MerchandiseAdapter(this);
//        adapter.addHeaderView(InitHeadView());
        adapter.setOnItemClickListener(onItemClickListener);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);//加载动画 可自定义 传入new BaseAnimation()
        adapter.setOnLoadMoreListener(requestLoadMoreListener);//滑动最后一个Item的时候回调onLoadMoreRequested方法

        refresh.setOnRefreshListener(onRefreshListener);
        adapter.setNewData(list);
        recyclerView.setAdapter(adapter);

    }

    private View InitHeadView() {

        View headView = View.inflate(this, R.layout.item_mall_head, null);
        marqueeView = headView.findViewById(R.id.marqueeView);


        //热门跑马灯
        SpannableString ss1 = new SpannableString("[红包]商城向你转账9.00元");
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        commList.add(ss1);

        SpannableString ss2 = new SpannableString("[/火热]秋季大酬宾");
        Drawable d = getResources().getDrawable(R.mipmap.icon_close);
        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
        //用这个drawable对象代替字符串火热
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
        ss2.setSpan(span, 0, 5, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        commList.add(ss2);

        commList.add("The record shows i took the blows");
        commList.add("习近平对全国道德模范表彰活动作出重要指示");
        hotCommAdapter = new HotCommAdapter(this,commList);
        marqueeView.setAdapter(hotCommAdapter);
        marqueeView.setIFlipListener(new MarqueeView.IFlipListener() {
            @Override
            public void onFilpStart(int position, View view) {
            }

            @Override
            public void onFilpSelect(int position, View view) {
            }
        });
        return headView;
    }

    private void refreshGetData() {
        list.clear();
        getData();
    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            list.add(new Merchandise());
        }
        if (list.size() > 0) {
            loadlayout.complete();
        } else {
            loadlayout.nulldata();
        }
        AddList();
    }


    /**
     * 点击布局背景 重新加载
     */
    LoadingErrorBgLayoutGroupView.OnErrorLoadListener onErrorLoadListener = new LoadingErrorBgLayoutGroupView.OnErrorLoadListener() {
        @Override
        public void againLoad() {
            refreshGetData();
        }
    };

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            if (isRefresh) {
                return;
            }
            isRefresh = true;
            loadlayout.load();
            refreshGetData();
        }
    };

    private void SetRefreshState(boolean b) {
        if (refresh != null) {
            if (b && !refresh.isRefreshing()) {
                refresh.setRefreshing(true);
            } else if (!b && refresh.isRefreshing()) {
                refresh.setRefreshing(false);
            }
        }
    }


    BaseQuickAdapter.RequestLoadMoreListener requestLoadMoreListener = new BaseQuickAdapter.RequestLoadMoreListener() {
        @Override
        public void onLoadMoreRequested() {

        }
    };

    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//            ARouter.getInstance().build(IntentConfig.TRACECONTENT_ACTIVITY).navigation();
        }
    };


    private void AddList() {
        isRefresh = false;
        SetRefreshState(isRefresh);
        adapter.loadMoreComplete();
        if (list.size() >= 20) {
            adapter.loadMoreEnd();
            return;
        }
        adapter.notifyDataSetChanged();
    }

}
