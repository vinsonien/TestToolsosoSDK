/*
 * ************************************************************
 * 文件：BuglyCrash.java  模块：onewarehouse  项目：TestWarehouse
 * 当前修改时间：2018年11月12日 11:10:27
 * 上次修改时间：2018年11月07日 10:36:04
 * 作者：S
 * Copyright (c) 2018
 * ************************************************************
 */

package com.vs.toolsoso.bugly;

import android.content.Context;

import com.blankj.utilcode.util.AppUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

public class BuglyCrash {
	
	public static final String BuglyID="";
	
	public static final String OnLineChannel="Online";
	public static final String TestChannel="Test";
	public static final String CodeeChannel="Code";
	public static final String ShowChannel="Show";

	public static final String DebugChannel="Debug";
	public static final String ReleaseChannel="Release";

	/**
	 * 初始化
	 * @param channel 渠道
	 * @param isdebug debug开关
	 */
	public static void InitCrash(Context c,String buglyId,String channel,boolean isdebug){
		UserStrategy strategy = new UserStrategy(c.getApplicationContext());
		strategy.setAppVersion(AppUtils.getAppVersionName());
		strategy.setAppPackageName(AppUtils.getAppPackageName());
		strategy.setAppChannel(channel);		
		CrashReport.initCrashReport(c.getApplicationContext(),buglyId,isdebug,strategy);
	}

	/**
	 * 提交一个异常
	 * @param e
	 */
	public static void PostCatchedException(Exception e){
		if(e!=null){
			CrashReport.postCatchedException(e);
		}
	}
	
	/**
	 * 
	 * @param id 用户标识
	 */
	public static void SetCrashUser(String id){
		CrashReport.setUserId(id);  //该用户本次启动后的异常日志用户ID
	}
}
