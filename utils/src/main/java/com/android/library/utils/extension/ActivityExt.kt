package com.android.library.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContract

inline fun <reified T> Activity.navigation(): Activity {
    startActivity(intent<T>())
    return this
}

inline fun <reified T> Activity.navigation(key: String, value: Any?): Activity {
    startActivity(intent<T>(key, value))
    return this
}

inline fun <reified T> Activity.navigation(key: String, value: Any?, flags: Int): Activity {
    startActivity(intent<T>(key, value, flags))
    return this
}

inline fun <reified T> Activity.navigation(vararg pairs: Pair<String, Any?>, flags: Int): Activity {
    startActivity(intent<T>(pairs = pairs, flags))
    return this
}

inline fun <reified T, reified D> ComponentActivity.navigationForResult(
    crossinline result: (t: D?) -> Unit,
    crossinline parse: (intent: Intent?) -> D?,
    vararg pairs: Pair<String, Any?>
) =
    registerForActivityResult(object : ActivityResultContract<Intent, D?>() {
        override fun createIntent(context: Context, input: Intent) = input
        override fun parseResult(resultCode: Int, intent: Intent?): D? {
            if (resultCode == Activity.RESULT_OK)
                return parse(intent)
            return null
        }
    }) {
        result(it)
    }.launch(intent<T>(pairs = pairs))

inline fun <reified T> Activity.receiveArguments(key: String): Any? =
    intent.receiveArguments<T>(key)

fun Activity.interval(
    millisInFuture: Long,
    countDownInterval: Long = 1000,
    onTick: (l: Long) -> Unit,
    onFinish: () -> Unit
) = object : CountDownTimer(millisInFuture, countDownInterval) {
    override fun onTick(l: Long) {
        onTick(l)
    }

    override fun onFinish() {
        onFinish()
    }
}.apply {
    if (isFinishing.or(isDestroyed)) {
        cancel()
        onFinish()
    } else {
        start()
    }
}


