package com.test.sdk.toolsoso;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.util.Utils;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.squareup.leakcanary.RefWatcher;
import com.vs.toolsoso.bugly.BuglyCrash;
import com.vs.toolsoso.leakcanary.LeakManager;
import com.vs.toolsoso.utils.LogUtil;

import org.xutils.x;

/**
 * @author: S
 * @date: 2019/5/7 14:24
 * @description:
 */
public class MyApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
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
     * 初始化 Utils
     */
    private void InitXUtils(boolean b) {
        x.Ext.init(this);
        x.Ext.setDebug(b); // 是否输出debug日志, 开启debug会影响性能.
    }



    /**
     * 日志库
     */
    private void InitLoggUtil(boolean isDebug) {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  //是否选择显示线程信息，默认为true
                .methodCount(0)         //方法数显示多少行，默认2行
                .methodOffset(7)        //隐藏方法内部调用到偏移量，默认5
                .tag("填写自己的TAG标签")   //自定义TAG全部标签，默认PRETTY_LOGGER
                .build();
        LogUtil.Init(formatStrategy, isDebug);
    }

    /**
     * bugly
     */
    private void InitCrashReport() {
        String buglyId = "平台里申请的appid";
        String CrashChannel = BuglyCrash.DebugChannel;
        BuglyCrash.InitCrash(this, buglyId, CrashChannel, true);
    }


    /**
     * Stetho
     */
    private void InitStetho(boolean isDebug) {
        if (isDebug) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
