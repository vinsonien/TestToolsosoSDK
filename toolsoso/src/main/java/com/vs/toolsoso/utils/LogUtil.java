package com.vs.toolsoso.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;

/**
 * @author: S
 * @date: 2018/10/31 17:49
 * @description:
 */

public class LogUtil {

    private static boolean IsDebug = true;
//    public static void Init() {
//        Logger.addLogAdapter(new AndroidLogAdapter());
//    }
//
//    public static void Init(String tag) {
//        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//                .tag(tag)//（可选）每个日志的全局标记。 默认PRETTY_LOGGER（如上图）
//                .build();
//        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
//    }

    public static void Init(FormatStrategy formatStrategy, boolean isDebug) {
        IsDebug = isDebug;
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }


    public static void e(String log) {
        if (IsDebug){
            Logger.e(log);
        }
    }

    public static void e(String tag, String log) {
        if (IsDebug){
            Logger.e(tag, log);
        }
    }

    public static void i(String log) {
        if (IsDebug){
            Logger.i(log);
        }
    }

    public static void i(String tag, String log) {
        if (IsDebug){
            Logger.i(tag, log);
        }
    }

    public static void d(String log) {
        if (IsDebug){
            Logger.d(log);
        }
    }

    public static void d(String tag, String log) {
        if (IsDebug){
            Logger.d(tag, log);
        }
    }
}
