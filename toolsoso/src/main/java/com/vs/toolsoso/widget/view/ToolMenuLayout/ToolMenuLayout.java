/*
 * 文件：ToolMenuLayout.java  模块：app  项目：Sunrise
 * 当前修改时间：2019年09月02日 12:03:18
 * 上次修改时间：2019年09月02日 12:03:18
 * 作者：vinsonien
 *
 * Copyright (c) 2019
 *
 */

package com.vs.toolsoso.widget.view.ToolMenuLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vs.toolsoso.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class ToolMenuLayout extends LinearLayout {

    private Context context;

    private int type = 0;//填充模式
    private int totItemCount = 0;//总项数
    private int rowItemCount = 0;//每行的项数
    private int rowCount = 0;//行数（商） = 总项数/每行的项数
    private int overCount = 0;//溢出数（余）= 总项数%每行的项数


    public static  String[] tabTextArray;//标题数组
    public static int[] tabIconArray;//图片数组

    private int textSize = 10;
    private int distance = 10;//icon text的距离
    private int paddingLeft = 0;
    private int paddingTop = 0;
    private int paddingRight = 0;
    private int paddingBottom = 0;
    private int textColor = Color.BLACK;
    private boolean ableClick = false;


    public static final int ALIGN_TYPE = 1;
    public static final int EQUAL_TYPE = 0;

    @IntDef({ALIGN_TYPE, EQUAL_TYPE})
    @Retention(RetentionPolicy.SOURCE)
    private @interface TypeMode {
    }

    OnToolMenuLayoutItemClickListener onMenuLayoutClickListener;


    public ToolMenuLayout(Context context) {
        super(context);
        this.context = context;
    }

    public ToolMenuLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        InitResource(context,attrs);
    }


    private void InitResource(Context context, AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.toolMenuLayout);
        float density = context.getResources().getDisplayMetrics().density;
        type = mTypedArray.getInt(R.styleable.toolMenuLayout_type,ALIGN_TYPE);
        textSize = mTypedArray.getInteger(R.styleable.toolMenuLayout_textSize,textSize);
        distance = mTypedArray.getInteger(R.styleable.toolMenuLayout_distance,textSize);
        paddingLeft = mTypedArray.getInteger(R.styleable.toolMenuLayout_paddingLeft,paddingLeft);
        paddingTop = mTypedArray.getInteger(R.styleable.toolMenuLayout_paddingTop,paddingTop);
        paddingRight = mTypedArray.getInteger(R.styleable.toolMenuLayout_paddingRight,paddingRight);
        paddingBottom = mTypedArray.getInteger(R.styleable.toolMenuLayout_paddingBottom,paddingBottom);
        textColor = mTypedArray.getColor(R.styleable.toolMenuLayout_textColor, textColor);
        mTypedArray.recycle();
    }


    public void init() {
        setOrientation(VERTICAL);

        if(tabTextArray ==null|| tabTextArray.length <=0 || tabIconArray ==null|| tabIconArray.length <=0 ){
            Log.e("toolsoso","数据不对[2]");
            return;
        }
        if(tabTextArray.length != tabIconArray.length ){
            Log.e("toolsoso","数据不对[3]");
            return;
        }
        if (rowItemCount <= 0){
            Log.e("toolsoso","数据不对[4]");
            return;
        }
        totItemCount = tabTextArray.length;

        rowCount = totItemCount / rowItemCount;
        overCount = totItemCount % rowItemCount;

        for (int i = 0; i < rowCount; i++) {
            LinearLayout rowLayout = buildItemLayout();
            for (int j = 0; j < rowItemCount; j++) {
                int position = rowItemCount * i+j;
                ToolMenuItem toolMenuItem= new ToolMenuItem(context);
                toolMenuItem.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT,1.0f));
                toolMenuItem.setTextSize(textSize);
                toolMenuItem.setTextColor(textColor);
                toolMenuItem.setText(tabTextArray[position]);
                toolMenuItem.setSrc(tabIconArray[position]);
                toolMenuItem.setDistance(distance);
                toolMenuItem.setItemPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
                if (ableClick){
                    toolMenuItem = setItemClickListener(toolMenuItem,position);
                }
                rowLayout.addView(toolMenuItem);
            }
            addView(rowLayout);
        }

        LinearLayout rowLayout = buildItemLayout();

        for (int i = 0; i < overCount; i++) {
            int position = rowItemCount * rowCount + i;
            ToolMenuItem toolMenuItem= new ToolMenuItem(context);
            toolMenuItem.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT,1.0f));
            toolMenuItem.setTextSize(textSize);
            toolMenuItem.setTextColor(textColor);
            toolMenuItem.setText(tabTextArray[position]);
            toolMenuItem.setSrc(tabIconArray[position]);
            toolMenuItem.setDistance(distance);
            toolMenuItem.setItemPadding(paddingLeft,paddingTop,paddingRight,paddingBottom);
            if (ableClick){
                toolMenuItem = setItemClickListener(toolMenuItem,position);
            }
            rowLayout.addView(toolMenuItem);
        }
        addView(rowLayout);
    }

    private LinearLayout buildItemLayout(){

        LinearLayout rowLayout = new LinearLayout(context);
        rowLayout.setOrientation(HORIZONTAL);
        //1：对齐每列， 0：平分空间
        if (type == ALIGN_TYPE) {
            rowLayout.setWeightSum(rowItemCount);
        }
        rowLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return rowLayout;
    }

    public void setOnMenuLayoutClickListener(OnToolMenuLayoutItemClickListener onMenuLayoutClickListener){
        this.onMenuLayoutClickListener =  onMenuLayoutClickListener;
        this.ableClick = true;
    }

    private ToolMenuItem setItemClickListener(ToolMenuItem item, final int position){
        item.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMenuLayoutClickListener != null){
                    onMenuLayoutClickListener.onItemClick(position);
                }
            }
        });
        return item;
    }

    public int getTotItemCount() {
        return totItemCount;
    }

    public int getRowItemCount() {
        return rowItemCount;
    }

    public void setRowItemCount(int rowItemCount) {
        this.rowItemCount = rowItemCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getOverCount() {
        return overCount;
    }

    public static String[] getTabTextArray() {
        return tabTextArray;
    }

    public static void setTabTextArray(String[] tabTextArray) {
        ToolMenuLayout.tabTextArray = tabTextArray;
    }

    public static int[] getTabIconArray() {
        return tabIconArray;
    }

    public static void setTabIconArray(int[] tabIconArray) {
        ToolMenuLayout.tabIconArray = tabIconArray;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getType() {
        return type;
    }

    public void setType(@TypeMode int type) {
        this.type = type;
    }

    public void setItemPadding(int left, int top, int right, int bottom){
        paddingLeft = left;
        paddingTop = top;
        paddingRight = right;
        paddingBottom = bottom;
    }

}
