package com.vs.toolsoso.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.blankj.utilcode.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @Created SiberiaDante
 * @Describe：
 * @CreateTime: 2018/1/16
 * @UpDateTime:
 * @Email: 2654828081@qq.com
 * @GitHub: https://github.com/SiberiaDante
 * @Blogs: http://www.cnblogs.com/shen-hua/
 */

public class DeviceUtil {

    /**
     * 获取sn号  适配 9.0、8.0~4.0、4.0-
     * @return
     */
    @SuppressLint({"NewApi", "MissingPermission"})
    public static String GetDeviceSN() {

        String serial = "";
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {//9.0+
                serial = Build.getSerial();
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {//8.0+
                serial = Build.SERIAL;
            } else {//8.0-
                Class<?> c = Class.forName("android.os.SystemProperties");
                Method get = c.getMethod("get", String.class);
                serial = (String) get.invoke(c, "ro.serialno");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("TAG", "读取设备序列号异常：" + e.toString());
        }
        return serial;
    }

    /**
     * 是否是华为
     */
    public static boolean isHUAWEI() {
        return android.os.Build.MANUFACTURER.equals("HUAWEI");
    }

    /**
     *     获取是否存在NavigationBar
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        try {
            Resources rs = context.getResources();
            int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
            if (id > 0) {
                hasNavigationBar = rs.getBoolean(id);
            }
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }


    @SuppressLint("MissingPermission")
    public static String GetDeviceId(Context c){
        TelephonyManager TelephonyMgr = (TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
        if(TelephonyMgr==null || TelephonyMgr.getDeviceId()==null || StringUtils.isEmpty(TelephonyMgr.getDeviceId())){
            return "0000000000";
        }else{
            return TelephonyMgr.getDeviceId();
        }
    }
}
