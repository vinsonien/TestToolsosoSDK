package com.vs.toolsoso.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author: S
 * @date: 2018/11/15 10:04
 * @description:
 */
public abstract class BaseSundryAdapter<T, K extends BaseViewHolder> extends BaseQuickAdapter<T, K > {


    public BaseSundryAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseSundryAdapter(@Nullable List<T> data) {
        super(data);
    }

    public BaseSundryAdapter(int layoutResId) {
        super(layoutResId);
    }


}
