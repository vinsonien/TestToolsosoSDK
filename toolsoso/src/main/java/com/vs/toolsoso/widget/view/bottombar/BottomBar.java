package com.vs.toolsoso.widget.view.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.vs.toolsoso.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by waiting on 2018/4/2.
 */

public class BottomBar extends LinearLayout implements View.OnClickListener{

    private Context mContext;

    private View divider;
    private LinearLayout barlayout;

    private static final int defTextColor= Color.BLACK;

    private int textNoPreColor = defTextColor;//文字默认颜色
    private int textPreColor = defTextColor;//文字选中颜色
    private int dividerColor = defTextColor;//分割线颜色

    private float textMarginIcon = 5;//文字与icon的距离
    private boolean isShowDivider=true;//是否显示分割线
    private int barBg=Color.WHITE;//背景颜色
    private int layoutPaddingTop=5,layoutPaddingBottom=5;
    private float textSize=16;//文字大小 单位:dp
    private float dividerHeight = 1;//分割线高度 单位:px
    private String[] textArray;//文字
    private int[] iconArray;//icon

    private BarOnTabChangeListener mBarOnTabChangeListener;
    private int choicePosition=-1;//当前选中的
    private List<BottomBarItem> mTabList;

    public BottomBar(Context context) {
        super(context,null);
        mContext=context;
    }

    public BottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        if(attrs !=null){
            TypedArray ta=context.obtainStyledAttributes(attrs, R.styleable.bootombar);
            setTextSize(ta.getInt(R.styleable.bootombar_text_size,15));
            setTextNoPreColor(ta.getColor(R.styleable.bootombar_text_defcolor,defTextColor));
            setTextPreColor(ta.getColor(R.styleable.bootombar_text_precolor,defTextColor));
            isShowDivider(ta.getBoolean(R.styleable.bootombar_show_divider,true));
            setDividerColor(ta.getColor(R.styleable.bootombar_divider_color,defTextColor));
            setDividerHeight(ta.getDimension(R.styleable.bootombar_divider_height,1));
            setBarBg(ta.getColor(R.styleable.bootombar_bar_bg,Color.WHITE));
            int top = (int)ta.getDimension(R.styleable.bootombar_bar_padding_top,5);
            int bottom =(int) ta.getDimension(R.styleable.bootombar_bar_padding_bottom,5);
            setBarPaddingTop(top,bottom);
            setTextMarginIcon((int)ta.getDimension(R.styleable.bootombar_text_margin_icon,5));

            ta.recycle();
        }
    }

    public void init(){
        LayoutInflater.from(mContext).inflate(R.layout.bottom_bar, this, true);
        divider=(View)findViewById(R.id.divider);
        barlayout=(LinearLayout)findViewById(R.id.barlayout);

        barlayout.setPadding(0,layoutPaddingTop,0,layoutPaddingBottom);
        barlayout.setBackgroundColor(barBg);
        divider.setBackgroundColor(dividerColor);
        divider.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,(int)dividerHeight));
        ShowDivider(isShowDivider);

        if(textArray ==null|| textArray.length <=0 || textArray.length != iconArray.length/2 ){
            Log.e("toolsoso","数据不对");
            return;
        }
        if(mTabList!=null){
            mTabList.clear();
        }else{
            mTabList = new ArrayList<>();
        }
        for (int i =0;i<textArray.length;i++){
            BottomBarItem barItem= new BottomBarItem(mContext);
            barItem.setTextSize(textSize);
            barItem.setNoPreColor(textNoPreColor);
            barItem.setPreColor(textPreColor);
            barItem.setNoPreIcon(iconArray[i*2]);
            barItem.setPreIcon(iconArray[i*2+1]);
            barItem.setTextMarginTop((int)textMarginIcon);
            barItem.setText(textArray[i]);
            barItem.setPosition(i);
            barItem.setOnClickListener(this);
            barItem.setNoPre();
            barlayout.addView(barItem);
            mTabList.add(barItem);
        }
        setChoiceItem(0);
    }

    public void setTextSize(float size){
        textSize = size;
    }

    public void setTextNoPreColor(int color){
        textNoPreColor = color;
    }

    public void setTextPreColor(int color){
        textPreColor = color;
    }

    public void isShowDivider(boolean flag){
        isShowDivider = flag;
    }

    public void setDividerColor(int color){
        dividerColor = color;
    }

    /**
     * 单位:px
     * @param height
     */
    public void setDividerHeight(float height){
       // ReadLog.Read_E("","dividerHeight="+height);
        dividerHeight = height;
    }

    /**
     * 整体背景
     * @param color
     */
    public void setBarBg(int color){
        barBg = color;
    }

    /**
     * 设置BarLayout内容上下Pingding
     */
    public void setBarPaddingTop(int top,int bottom){
//        LogUtil.e("top="+top);
//        LogUtil.e("bottom="+bottom);
        layoutPaddingTop=top;
        layoutPaddingBottom=bottom;
    }


    public void setTextMarginIcon(float i){
        textMarginIcon = i;
//        LogUtil.e("textMarginIcon="+textMarginIcon);
    }


    public void setTextArray(String[] array){
        textArray = array;
    }

    public void setIconArray(int[] array){
        iconArray = array;
    }

    public void setBarOnTabChangeListener(BarOnTabChangeListener listener){
        mBarOnTabChangeListener = listener;
    }

    public void ShowDivider(boolean flag){
        if(flag){
            divider.setVisibility(View.VISIBLE);
        }else{
            divider.setVisibility(View.GONE);
        }
    }

    public void setPosition(int position){
        if(mTabList == null || mTabList.size()<=0){
            return;
        }
        if(choicePosition == position){
            return;
        }
        int size = mTabList.size();
        for (int i=0;i<size;i++){
            BottomBarItem mBottomBarItem=mTabList.get(i);
            if(i==position){
                mBottomBarItem.setPre();
                choicePosition = i;
            }else{
                mBottomBarItem.setNoPre();
            }
        }
    }

    public void setChoiceItem(int position){
       // int position = item.GetPosition();
        if(mTabList == null || mTabList.size()<=0){
            return;
        }
        if(choicePosition == position){
            return;
        }
        int size = mTabList.size();
        for (int i=0;i<size;i++){
            BottomBarItem mBottomBarItem=mTabList.get(i);
            if(i==position){
                if(mBarOnTabChangeListener!=null){
                    mBarOnTabChangeListener.OnTabChange(mBottomBarItem.GetPosition(),mBottomBarItem);
                }
                mBottomBarItem.setPre();
                choicePosition = i;
            }else{
                mBottomBarItem.setNoPre();
            }
        }
    }

    public int getChoicePosition(){
        return choicePosition;
    }

    @Override
    public void onClick(View v) {
        if(mBarOnTabChangeListener!=null){
            BottomBarItem mBottomBarItem=(BottomBarItem)v;
            setChoiceItem(mBottomBarItem.GetPosition());
        }
    }
}
