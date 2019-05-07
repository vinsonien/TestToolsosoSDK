package com.vs.toolsoso.leakcanary;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * @author: S
 * @date: 2019/2/15 14:31
 * @description:
 */
public class LeakManager {

    /**
     * 内存泄漏检测
     * @param context
     */
    public static RefWatcher Init(Application context) {
//            if (LeakCanary.isInAnalyzerProcess(context)) {
//                // This process is dedicated to LeakCanary for heap analysis.
//                // You should not init your app in this process.
//                return;
//            }
            return LeakCanary.install(context);
            // Normal app init code...
    }
}
