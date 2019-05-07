package com.vs.toolsoso.lifecycle;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

/**
 * @author: S
 * @date: 2018/11/23 16:04
 * @description:
 */
public class BaseLifecycle implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onLifecycleCreate(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onLifecycleStart(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onLifecycleResume(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onLifecyclePause(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onLifecycleStop(){

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onLifecycleDestroy(){

    }
}
