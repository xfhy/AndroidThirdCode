package com.xfhy.learnkotlin.jetpack.lifecycles

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/**
 * @author : xfhy
 * Create time : 2020-04-19 16:58
 * Description : 将Lifecycle实例传入,可以在任何方法中获取当前的生命周期状态
 */
class MyObserver(private val lifecycle: Lifecycle) : LifecycleObserver {

    companion object {
        const val TAG = "MyObserver"
    }

    fun testGetLifecycleState() {
        val currentState = lifecycle.currentState
        Log.i(TAG, "$currentState")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d(TAG, "activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.i(TAG, "activityStop")
    }

}