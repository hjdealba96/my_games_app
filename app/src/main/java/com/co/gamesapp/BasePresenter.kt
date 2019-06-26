package com.co.gamesapp

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.lang.ref.WeakReference

abstract class BasePresenter<V : LifecycleOwner> : LifecycleObserver {

    private var view: WeakReference<V>? = null

    fun setView(view: V) {
        view.lifecycle.addObserver(this)
        this.view = WeakReference(view)
    }

    fun getView(): V? = view?.get()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    abstract fun init()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun finish() {
        getView()?.lifecycle?.removeObserver(this)
    }

}