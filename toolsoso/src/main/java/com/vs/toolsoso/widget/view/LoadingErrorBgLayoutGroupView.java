package com.vs.toolsoso.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.vs.toolsoso.R;
import com.vs.toolsoso.utils.ScreenUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author: S
 * @date: 2018/12/29 10:34
 * @description:
 */
public class LoadingErrorBgLayoutGroupView extends RelativeLayout {


    Context context;
    View mainView;

    public RelativeLayout fatherLayout;
    public TextView tipTv;

    String nullTipStr;//加载成功 数据为空的提示
    String ingTipStr;//加载中的文本提示
    String errorTipStr;//加载错误的文本提示
    float tipSize;//字体大小
    int tipColor;//字体颜色

    float defSize = 15;
    int defColor = Color.BLACK;

    //加载状态模式
    public int CurrMode = -1;
    public static final int ERROR_MODE = 1;//加载载失败
    public static final int ING_MODE = 2;//加载中
    public static final int COMPLETE_MODE = 3;//加载完成
    public static final int NULL_MODE = 4;//加载结束 没有数据

    @IntDef({ERROR_MODE, ING_MODE, COMPLETE_MODE, NULL_MODE})
    @Retention(RetentionPolicy.SOURCE)
    private @interface BuildMode {
    }

    public LoadingErrorBgLayoutGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        Init(attrs);
    }

    public LoadingErrorBgLayoutGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        Init(attrs);
    }

    private void Init(AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.layout_error_bg_layout, this, true);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.loadlayout_tv);
            setTipSize(ta.getDimension(R.styleable.loadlayout_tv_tip_size, defSize));
            setTipColor(ta.getColor(R.styleable.loadlayout_tv_tip_color, defColor));
            setIngTipStr(ta.getString(R.styleable.loadlayout_tv_ing_text));
            setErrorTipStr(ta.getString(R.styleable.loadlayout_tv_error_text));
            setNullTipStr(ta.getString(R.styleable.loadlayout_tv_null_text));
            ta.recycle();
        }

        fatherLayout = findViewById(R.id.fatherLayout);
        tipTv = fatherLayout.findViewById(R.id.tipTv);
        tipTv.setText(ingTipStr);
        tipTv.setTextColor(getTipColor());
        tipTv.setTextSize(ScreenUtil.px2dip(context, getTipSize()));
        tipTv.setOnClickListener(onClickListener);

        load();
    }


    public View getMainView() {
        return mainView;
    }

    public void bindMainView(View mainView) {
        this.mainView = mainView;
    }

    public void nulldata() {
        if (mainView == null) {
            return;
        }
        mainView.setVisibility(GONE);
        tipTv.setVisibility(VISIBLE);
        CurrMode = NULL_MODE;
        tipTv.setTag(CurrMode);

        if (StringUtils.isEmpty(nullTipStr)) {
            tipTv.setText(context.getString(R.string.load_null));
        } else {
            tipTv.setText(nullTipStr);
        }
    }

    public void complete() {
        if (mainView == null) {
            return;
        }
        mainView.setVisibility(VISIBLE);
        tipTv.setVisibility(GONE);
        CurrMode = COMPLETE_MODE;
        tipTv.setTag(CurrMode);
    }

    public void error() {
        if (mainView == null) {
            return;
        }
        mainView.setVisibility(GONE);
        tipTv.setVisibility(VISIBLE);
        CurrMode = ERROR_MODE;
        tipTv.setTag(CurrMode);

        if (StringUtils.isEmpty(errorTipStr)) {
            tipTv.setText(context.getString(R.string.load_error));
        } else {
            tipTv.setText(errorTipStr);
        }
//        LogUtil.e("   *** error后 ***   "
//                + "\n   CurrMode===" + CurrMode
//                + "\n   tipTv.getTag()===" + tipTv.getTag()
//                + "\n   tipTv.getText()===" + tipTv.getText().toString().trim());
    }


    public void load() {
        if (mainView == null) {
            return;
        }
        mainView.setVisibility(GONE);
        tipTv.setVisibility(VISIBLE);
        CurrMode = ING_MODE;
        tipTv.setTag(CurrMode);

        if (StringUtils.isEmpty(ingTipStr)) {
            tipTv.setText(context.getString(R.string.load_ing));
        } else {
            tipTv.setText(ingTipStr);
        }
//        LogUtil.e("   *** load后 ***   "
//                + "\n   CurrMode===" + CurrMode
//                + "\n   tipTv.getTag()===" + tipTv.getTag()
//                + "\n   tipTv.getText()===" + tipTv.getText().toString().trim());
    }

    public boolean IsShowTip() {
        return CurrMode != COMPLETE_MODE;
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            int i = v.getId();
            if (i == R.id.tipTv) {
                if (CurrMode == ERROR_MODE) {
                    load();
                    if (errorLoadListener != null) {
                        errorLoadListener.againLoad();
                    }
                }
                return;
            }
        }
    };


    private OnErrorLoadListener errorLoadListener;

    public interface OnErrorLoadListener {
        void againLoad();
    }

    public void setOnErrorLoadListener(OnErrorLoadListener errorLoadListener) {
        this.errorLoadListener = errorLoadListener;
    }


    public String getNullTipStr() {
        return nullTipStr;
    }

    public void setNullTipStr(String nullTipStr) {
        this.nullTipStr = nullTipStr;
    }

    public String getIngTipStr() {
        return ingTipStr;
    }

    public void setIngTipStr(String ingTipStr) {
        this.ingTipStr = ingTipStr;
    }

    public float getTipSize() {
        return tipSize;
    }

    public void setTipSize(float tipSize) {
        this.tipSize = tipSize;
    }

    public int getTipColor() {
        return tipColor;
    }

    public void setTipColor(int tipColor) {
        this.tipColor = tipColor;
    }

    public String getErrorTipStr() {
        return errorTipStr;
    }

    public void setErrorTipStr(String errorTipStr) {
        this.errorTipStr = errorTipStr;
    }
}
