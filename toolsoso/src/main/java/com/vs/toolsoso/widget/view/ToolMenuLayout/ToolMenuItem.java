package com.vs.toolsoso.widget.view.ToolMenuLayout;

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




public class ToolMenuItem extends LinearLayout{

    private Context mContext;

    private static final int defTextColor= Color.BLACK;

   private ImageView iv_icon;
   private TextView tv_text;

    private float textSize=20;
    private int bgColor=Color.WHITE;
    private String text;


    public ToolMenuItem(Context context) {
        super(context,null);
        mContext = context;
        Init();
    }

    public ToolMenuItem(Context context, @Nullable AttributeSet attrs) {
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

    public void setSrc(int resourceId) {
        iv_icon.setImageResource(resourceId);
    }

    public void setTextColor(int colorId){
        tv_text.setTextColor(colorId);
    }

    public void setText(String str){
        text = str;
        tv_text.setText(text);
    }

    public void setItemBg(int color){
        bgColor = color;
        setBackgroundColor(bgColor);
    }


    public void setDistance(int distance){
        LayoutParams lp= new LayoutParams(tv_text.getLayoutParams());
        lp.setMargins(0,distance,0,0);
        tv_text.setLayoutParams(lp);
    }

    public void setItemPadding(int left, int top, int right, int bottom){
        setPadding(left,top,right,bottom);
    }
}
