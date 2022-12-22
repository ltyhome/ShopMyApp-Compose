package com.android.library.utils.extension

import android.os.Build
import android.os.Bundle
import android.os.Parcelable

inline fun <reified T> Bundle.receiveArguments(key: String): Any? = when (T::class.java) {
    String::class.java -> getString(key)
    Boolean::class.java -> getBoolean(key, false)
    Int::class.java -> getInt(key, 0)
    Long::class.java -> getLong(key, 0)
    Double::class.java -> getDouble(key, 0.0)
    Parcelable::class.java -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getParcelable(
        key,
        T::class.java
    ) else getParcelable(key)
    Bundle::class.java -> getBundle(key)
    Array::class.java -> {
        val componentType = T::class.java.componentType!!
        when {
            Parcelable::class.java.isAssignableFrom(componentType) -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    getParcelableArray(key, T::class.java)
                else
                    getParcelableArray(key)
            }
            String::class.java.isAssignableFrom(componentType) -> {
                getStringArray(key)
            }
            else -> null
        }
    }
    List::class.java -> {
        val componentType = T::class.java.componentType!!
        when {
            Parcelable::class.java.isAssignableFrom(componentType) -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    getParcelableArrayList(key, T::class.java)
                else
                    getParcelableArrayList(key)
            }
            String::class.java.isAssignableFrom(componentType) -> {
                getStringArrayList(key)
            }
            else -> null
        }
    }
    else -> null
}