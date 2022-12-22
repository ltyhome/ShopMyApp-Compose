package com.android.library.utils.extension

import android.content.Intent
import android.os.Build
import android.os.Parcelable

inline fun <reified T> Intent.receiveArguments(key: String): Any? = when (T::class.java) {
    String::class.java -> getStringExtra(key)
    Boolean::class.java -> getBooleanExtra(key, false)
    Int::class.java -> getIntExtra(key, 0)
    Long::class.java -> getLongExtra(key, 0)
    Double::class.java -> getDoubleExtra(key, 0.0)
    Parcelable::class.java -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) getParcelableExtra(
        key,
        T::class.java
    ) else getParcelableExtra(key)

    ArrayList::class.java -> {
        val componentType = T::class.java.componentType!!
        when {
            Parcelable::class.java.isAssignableFrom(componentType) -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    getParcelableArrayListExtra(key, T::class.java)
                else
                    getParcelableArrayListExtra(key)
            }

            String::class.java.isAssignableFrom(componentType) -> {
                getStringArrayListExtra(key)
            }

            else -> null
        }
    }

    else -> null
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Intent.setArguments(key: String, value: T?): Intent {
    when (T::class.java) {
        String::class.java -> putExtra(key, value as? String)
        Boolean::class.java -> putExtra(key, value as? Boolean)
        Int::class.java -> putExtra(key, value as? Int)
        Long::class.java -> putExtra(key, value as? Long)
        Double::class.java -> putExtra(key, value as? Double)
        Parcelable::class.java -> putExtra(key, value as? Parcelable)
        ArrayList::class.java -> {
            val componentType = T::class.java.componentType!!
            when {
                Parcelable::class.java.isAssignableFrom(componentType) -> putParcelableArrayListExtra(
                    key,
                    value as? ArrayList<Parcelable>
                )

                String::class.java.isAssignableFrom(componentType) -> {
                    putStringArrayListExtra(key, value as? ArrayList<String>)
                }
            }
        }
    }
    return this
}