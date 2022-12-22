package com.android.library.utils.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment

inline fun <reified T> Fragment.navigation() = activity?.navigation<T>()

inline fun <reified T> Fragment.navigation(key: String, value: Any?) =
    activity?.navigation<T>(key, value)

inline fun <reified T> Fragment.navigation(key: String, value: Any?, flags: Int) =
    activity?.navigation<T>(key, value, flags)

inline fun <reified T> Fragment.navigation(vararg pairs: Pair<String, Any?>, flags: Int) =
    activity?.navigation<T>(pairs = pairs, flags)

inline fun <reified T, reified D> Fragment.navigationForResult(
    crossinline result: (result: D?) -> Unit,
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
    }.launch(context?.intent<T>(*pairs))

inline fun <reified T> Fragment.receiveArguments(key: String): Any? =
    arguments?.receiveArguments<T>(key)

fun Fragment.checkSelfPermissions(vararg permissions: String) =
    context?.checkSelfPermissions(*permissions)

fun Fragment.interval(
    millisInFuture: Long,
    countDownInterval: Long = 1000,
    onTick: (l: Long) -> Unit,
    onFinish: () -> Unit
) = activity?.interval(millisInFuture, countDownInterval, onTick, onFinish)