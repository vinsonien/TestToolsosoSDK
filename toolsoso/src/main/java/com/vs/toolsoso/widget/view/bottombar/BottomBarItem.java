package com.vs.toolsoso.widget.view.bottombar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vs.toolsoso.R;


/**
 * Created by waiting on 2018/4/2.
 */

public class BottomBarItem extends LinearLayout{

    private Context mContext;

    private static final int defTextColor= Color.BLACK;

   private ImageView iv_icon;
   private TextView tv_text;

    private float textSize=20;
    private int noPreColor = defTextColor;
    private int preColor = defTextColor;
    private int bgColor=Color.WHITE;
    private int noPreIcon,preIcon;
    private String text;
    private int position;


    public BottomBarItem(Context context) {
        super(context,null);
        mContext = context;
        Init();
    }

    public BottomBarItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        Init();
    }


    private void Init(){
        LayoutInflater.from(mContext).inflate(R.layout.bottom_tab_item, this, true);
        setBackgroundColor(bgColor);
        iv_icon = (ImageView)findViewById(R.id.iv_icon);
        tv_text = (TextView)findViewById(R.id.tv_text);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,1.0f));
    }


    public void setTextSize(float size){
      //  textSize =ToolUtils.dip2px(mContext,size);
        textSize = size;
        tv_text.setTextSize(textSize);
    }

    public void setNoPreColor(int color){
        noPreColor = color;
    }

    public void setPreColor(int color){
        preColor = color;
    }

    public void setNoPreIcon(int i){
        noPreIcon =i;
    }

    public void setPreIcon(int i){
        preIcon =i;
    }

    public void setText(String str){
        text = str;
        tv_text.setText(text);
    }

    public void setItemBg(int color){
        bgColor = color;
        setBackgroundColor(bgColor);
    }

    public void setPosition(int i){
        position = i;
        setTag(i);
    }

    public int GetPosition(){
        return position;
    }

    public void setNoPre(){
        iv_icon.setImageResource(noPreIcon);
        tv_text.setTextColor(noPreColor);
    }

    public void setPre(){
        iv_icon.setImageResource(preIcon);
        tv_text.setTextColor(preColor);
    }


    public void setTextMarginTop(int marginTop){
        LayoutParams lp= new LayoutParams(tv_text.getLayoutParams());
        lp.setMargins(0,marginTop,0,0);
        tv_text.setLayoutParams(lp);
    }

}
