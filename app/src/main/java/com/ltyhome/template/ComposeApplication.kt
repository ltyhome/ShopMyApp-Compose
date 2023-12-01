package com.ltyhome.template

import android.app.Application

class ComposeApplication:Application() {
    override fun onCreate() {
        useStrictMode()
        super.onCreate()
    }
}