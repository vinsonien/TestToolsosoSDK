package com.vs.toolsoso.eventbus;


import org.simple.eventbus.EventBus;

/**
 * @author: S
 * @date: 2018/11/14 10:36
 * @description:
 */

public class EvenBusManager {

	public static void UnRegister(Object ob) {
		EventBus.getDefault().unregister(ob);
	}

	public static void RegisterSticky(Object ob) {
		EventBus.getDefault().registerSticky(ob);
	}

	public static void PostSticky(Object ob, String tag) {
		EventBus.getDefault().postSticky(ob, tag);
	}

	public static <T> void RemoveStickyEvent(Class<T> c, String tag) {
		EventBus.getDefault().removeStickyEvent(c, tag);
	}

	public static void Register(Object ob) {
		EventBus.getDefault().register(ob);
	}

	public static void Post(Object ob, String tag) {
		EventBus.getDefault().post(ob, tag);
	}

}
