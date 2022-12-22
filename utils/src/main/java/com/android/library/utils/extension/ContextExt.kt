package com.android.library.utils.extension

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf

inline fun <reified T> Context.intent(
    key: String, value: Any?, flags: Int = 0
) = Intent(this, T::class.java).setArguments(key, value).addFlags(flags)

inline fun <reified T> Context.intent(
    vararg pairs: Pair<String, Any?>, flags: Int = 0
) = Intent(this, T::class.java).putExtras(bundleOf(*pairs)).addFlags(flags)

inline fun <reified T> Context.navigation(block: Intent.() -> Unit) =
    startActivity(Intent(this, T::class.java).apply(block))

fun Context.checkSelfPermissions(vararg permissions: String): Boolean {
    var isGranted = true
    permissions.forEach {
        isGranted = isGranted && ActivityCompat.checkSelfPermission(
            this,
            it
        ) == PackageManager.PERMISSION_GRANTED
    }
    return isGranted
}