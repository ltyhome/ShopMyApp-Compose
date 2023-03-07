package com.android.shopmy

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.startup.Initializer
import com.android.library.utils.MKOwner
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVLogLevel

class ShopMyInitializer : Initializer<Unit>, LifecycleObserver {
  override fun create(context: Context) {
    ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    if (MKOwner.default == null) {
      MMKV.initialize(context, MMKVLogLevel.LevelNone)
      MKOwner.default = MMKV.defaultMMKV()
    }
  }

  override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}
