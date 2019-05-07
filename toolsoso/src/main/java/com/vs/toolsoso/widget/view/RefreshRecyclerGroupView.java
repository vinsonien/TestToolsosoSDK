package com.vs.toolsoso.widget.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vs.toolsoso.R;


/**
 * @author: S
 * @date: 2018/11/16 9:50
 * @description:
 */
public class RefreshRecyclerGroupView extends LinearLayout {

    private Context context;

    public SwipeRefreshLayout swipe;
    public RecyclerView recy;

    public RefreshRecyclerGroupView(Context context) {
        super(context);
        this.context = context;
        Init();
    }

    public RefreshRecyclerGroupView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Init();
    }

    private void Init(){
        LayoutInflater.from(this.context).inflate(R.layout.view_refresh_recyclerview, this, true);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,1.0f));

        swipe = findViewById(R.id.refresh);
        recy = findViewById(R.id.recyerview);
    }

}
