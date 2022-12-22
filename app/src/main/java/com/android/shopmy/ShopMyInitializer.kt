package com.android.shopmy

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.startup.Initializer

class ShopMyInitializer: Initializer<Unit>, LifecycleObserver {
    override fun create(context: Context) {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}