package com.test.sdk.toolsoso.marqueeview.delegate;

import android.widget.TextView;

import com.test.sdk.toolsoso.R;
import com.test.sdk.toolsoso.bean.MultiTypeBean;
import com.vs.toolsoso.widget.view.MarqueeView.base.ItemViewDelegate;
import com.vs.toolsoso.widget.view.MarqueeView.base.ViewHolder;


/**
 * Created by xujun on 1/9/2018$ 18:25$.
 */
public class TextItemViewDelegate implements ItemViewDelegate<MultiTypeBean> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_simple_text;
    }

    @Override
    public boolean isForViewType(MultiTypeBean item, int position) {
        return item.mItemViewType == MultiTypeBean.ItemViewType.text;
    }

    @Override
    public void convert(ViewHolder holder, MultiTypeBean multiTypeBean, int position) {
        TextView tv = holder.getView(R.id.tv);
        tv.setText(multiTypeBean.title);
    }


}
