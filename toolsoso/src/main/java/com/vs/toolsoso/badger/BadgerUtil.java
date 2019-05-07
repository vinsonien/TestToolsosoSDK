package com.vs.toolsoso.badger;

import android.content.Context;
import android.util.Log;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * @author: S
 * @date: 2019/2/21 11:42
 * @description:
 */
public class BadgerUtil {

    private static final String TAG = "BadgerUtil";

    /**
     * 展示badger
     * @param context
     * @param badgeCount
     * @return
     */
    public static boolean Show(Context context, int badgeCount){

        if (badgeCount<=0){
            Log.e(TAG," badgeCount error input");
            return false;
        }
        return ShortcutBadger.applyCount(context, badgeCount);
    }

    /**
     * 移除badger
     * @param context
     * @return
     */
    public static boolean Remove(Context context){
        return ShortcutBadger.removeCount(context);
    }
}
