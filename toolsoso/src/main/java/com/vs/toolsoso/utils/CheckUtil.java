package com.vs.toolsoso.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

/**
 * @author: S
 * @date: 2019/2/15 11:40
 * @description:
 */
public class CheckUtil {

    /**
     * 判断当前应用是否是debug状态
     */

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *检测其他应用是否处于debug模式。
     */
    public static boolean isApkDebugable(Context context,String packageName) {
        try {
            @SuppressLint("WrongConstant") PackageInfo pkginfo = context.getPackageManager().getPackageInfo(
                    packageName, 1);
            if (pkginfo != null ) {
                ApplicationInfo info= pkginfo.applicationInfo;
                return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
            }

        } catch (Exception e) {

        }
        return false;
    }
}
