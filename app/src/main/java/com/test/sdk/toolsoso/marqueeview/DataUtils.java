package com.test.sdk.toolsoso.marqueeview;

import android.support.annotation.DrawableRes;

import com.test.sdk.toolsoso.R;

/**
 * Created by xujun on 1/9/2018$ 18:10$.
 */
public class DataUtils {

    public static String produceTitle(int position) {
        String content = String.format("I am %s handsome boy", position);
        return content;
    }

    public static String produceContent(int position) {
        String content = String.format("为我点赞 + %s", position);
        return content;
    }

    public static @DrawableRes
    int produceImageResId(int position) {
        if ((position & 1) == 0) {
            return R.mipmap.ic_launcher_round;
        }
        return R.mipmap.ic_launcher_round;
    }
}
