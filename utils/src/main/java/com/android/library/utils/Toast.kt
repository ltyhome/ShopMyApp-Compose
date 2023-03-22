package com.android.library.utils

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import androidx.annotation.Keep
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun Context.success(msg: String) = toast(msg, R.drawable.ic_success)
fun Context.error(msg: String) = toast(msg, R.drawable.ic_error)
fun Context.warning(msg: String) = toast(msg, R.drawable.ic_warning)
fun Context.info(msg: String) = toast(msg, R.drawable.ic_info)

inline fun <reified T : Context> T.toast(msg: String, icon: Int) = Toast(applicationContext).apply {
    object : CountDownTimer(5000L, 1000) {
        override fun onTick(millisUntilFinished: Long) {}
        override fun onFinish() {
            this@apply.cancel()
        }
    }.start()
    setGravity(20, 0, 0)
    view = ComposeView(this@toast).apply {
        setContent {
            StateToast(state = State(msg, icon))
        }
    }
    show()
}

@Composable
fun StateToast(state: State) {
    Column(
        modifier = Modifier
            .background(Color(208, 208, 208), RoundedCornerShape(10.dp))
            .padding(start = 18.dp, end = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = state.icon),
            contentDescription = null,
            Modifier.padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = state.msg,
            fontSize = 12.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
}

@Keep
data class State(
    val msg: String,
    val icon: Int
)


