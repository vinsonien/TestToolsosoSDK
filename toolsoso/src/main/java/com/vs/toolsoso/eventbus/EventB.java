package com.vs.toolsoso.eventbus;


import org.greenrobot.eventbus.EventBus;

/**
 * @author: S
 * @date: 2018/11/14 10:36
 * @description:
 */

public class EventB {

    public static void unRegister(Object ob) {
        if (EventBus.getDefault().isRegistered(ob))
            EventBus.getDefault().unregister(ob);
    }

    public static void register(Object ob) {
        if (!EventBus.getDefault().isRegistered(ob))
            EventBus.getDefault().register(ob);
    }

    public static void removeStickyEvent(Object ob){
        EventBus.getDefault().removeStickyEvent(ob);
    }

    public static <T> T removeStickyEvent(Class<T> eventType){
        return EventBus.getDefault().removeStickyEvent(eventType);
    }

    public static void removeAllStickyEvent(){
        EventBus.getDefault().removeAllStickyEvents();
    }

    public static <T> T getStickyEvent(Class<T> eventType){
        return EventBus.getDefault().getStickyEvent(eventType);
    }


    public static void post(Object ob){
        EventBus.getDefault().post(ob);
    }

    public static void postSticky(Object ob){
        EventBus.getDefault().postSticky(ob);
    }


}
