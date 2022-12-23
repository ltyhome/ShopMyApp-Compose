package com.android.library.utils.extension

import android.widget.TextView

inline fun <reified T : TextView> T.condition(
    condition: T.() -> Boolean,
    block: T.() -> Unit
): Boolean {
    if (condition()) {
        block()
        return false
    }
    return true
}