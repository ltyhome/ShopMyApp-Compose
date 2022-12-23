package com.android.library.utils.extension

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.children

inline fun <reified T : View> T.visible() {
    this.visibility = View.VISIBLE
}

inline fun <reified T : View> Array<T>.visible() {
    forEach {
        it.visible()
    }
}

inline fun <reified T : View> T.isVisible() = this.visibility == View.VISIBLE

inline fun <reified T : View> T.gone() {
    clearAnimation()
    this.visibility = View.GONE
}

inline fun <reified T : View> Array<T>.gone() {
    forEach {
        it.gone()
    }
}

inline fun <reified T : View> T.selected() {
    this.isSelected = true
}

inline fun <reified T : View> Array<T>.selected() {
    forEach {
        it.selected()
    }
}

fun ViewGroup.selected() {
    isSelected = true
    children.forEach {
        if (it is ViewGroup)
            it.selected()
        else
            it.selected()
    }
}

inline fun <reified T : View> T.unSelected() {
    this.isSelected = false
}

inline fun <reified T : View> Array<T>.unSelected() {
    forEach {
        it.unSelected()
    }
}

fun ViewGroup.unSelected() {
    isSelected = false
    children.forEach {
        if (it is ViewGroup)
            it.unSelected()
        else
            it.unSelected()
    }
}

inline fun <reified T : View> T.enable() {
    isEnabled = true
    isClickable = true
}

inline fun <reified T : View> T.disable() {
    isEnabled = false
    isClickable = false
    clearFocus()
}

inline fun <reified T : TextView> T.clear() {
    when (this) {
        is EditText -> setText("")
        else -> text = ""
    }
}

inline fun <reified T : TextView> Array<T>.clear() {
    forEach {
        it.clear()
    }
}

fun View.convertViewToBitmap(desiredWidth: Int? = null): Bitmap {
    if (measuredWidth == 0 || measuredHeight == 0) {
        measure(
            View.MeasureSpec.makeMeasureSpec(
                context.resources.displayMetrics.widthPixels,
                View.MeasureSpec.AT_MOST
            ),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        layout(0, 0, this.measuredWidth, this.measuredHeight)
    }
    var bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    val backgroundDrawable = background
    backgroundDrawable?.run {
        draw(canvas)
    } ?: canvas.drawColor(Color.WHITE)
    draw(canvas)
    desiredWidth?.let {
        val matrix = Matrix()
        val scaleWidth = it.toFloat() / measuredWidth
        val scaleHeight = it.toFloat() * measuredHeight / measuredWidth / measuredHeight
        matrix.postScale(scaleWidth, scaleHeight)
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, measuredWidth, measuredHeight, matrix, true)
    }
    return bitmap
}