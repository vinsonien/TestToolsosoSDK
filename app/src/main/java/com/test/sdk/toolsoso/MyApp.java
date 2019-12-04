package com.test.sdk.toolsoso;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.RefWatcher;
import com.vs.toolsoso.bugly.BuglyCrash;
import com.vs.toolsoso.leakcanary.LeakManager;


/**
 * @author: S
 * @date: 2019/5/7 14:24
 * @description:
 */
public class MyApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        InitBlankjUtils();
    }

    /**
     * 内存泄漏检测
     */
    private RefWatcher refWatcher;
    public static RefWatcher getRefWatcher(Context context) {
        MyApp application = (MyApp) context.getApplicationContext();
        return application.refWatcher;
    }
    private void InitLeakCanary() {
        refWatcher = LeakManager.Init(this);
    }

    /**
     * Blankj 常用工具
     */
    private void InitBlankjUtils() {
        Utils.init(this);
    }


    /**
     * bugly
     */
    private void InitCrashReport() {
        String buglyId = "平台里申请的appid";
        String CrashChannel = BuglyCrash.DebugChannel;
        BuglyCrash.InitCrash(this, buglyId, CrashChannel, true);
    }

}
