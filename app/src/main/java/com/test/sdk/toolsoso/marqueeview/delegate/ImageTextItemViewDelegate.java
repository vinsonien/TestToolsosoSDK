package com.test.sdk.toolsoso.marqueeview.delegate;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.sdk.toolsoso.R;
import com.test.sdk.toolsoso.bean.MultiTypeBean;
import com.test.sdk.toolsoso.glide.GlideWith;
import com.vs.toolsoso.widget.view.MarqueeView.base.ItemViewDelegate;
import com.vs.toolsoso.widget.view.MarqueeView.base.ViewHolder;

import java.net.ContentHandler;


/**
 * Created by xujun on 1/9/2018$ 18:25$.
 */
public class ImageTextItemViewDelegate implements ItemViewDelegate<MultiTypeBean> {

    Context context;

    public ImageTextItemViewDelegate(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_image_text;
    }

    @Override
    public boolean isForViewType(MultiTypeBean item, int position) {
        return item.mItemViewType == MultiTypeBean.ItemViewType.imageText;
    }

    @Override
    public void convert(ViewHolder holder, MultiTypeBean multiTypeBean, int position) {
        TextView tv = holder.getView(R.id.tv);
        tv.setText(multiTypeBean.title);

        ImageView iv = holder.getView(R.id.iv);
        iv.setImageResource(multiTypeBean.resImageId);
        GlideWith.ShowAngle(context,iv,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=23719042,612560189&fm=26&gp=0.jpg");
    }



}
