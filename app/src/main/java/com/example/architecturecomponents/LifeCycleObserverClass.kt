package com.example.architecturecomponents

import android.app.Activity
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifeCycleObserverClass(var activity: Activity) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateListener() {
        Log.e("TAG", "==>> On Create")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeListener() {
        Log.e("TAG", "==>> On Resume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseListener() {
        Log.e("TAG", "==>> On Pause")
    }
}