package com.ltyhome.template

import android.os.StrictMode
import androidx.fragment.app.strictmode.FragmentStrictMode

fun useStrictMode() {
    StrictMode.setThreadPolicy(
        StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()
    )
    StrictMode.setVmPolicy(
        StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyDeath()
            .build()
    )
    FragmentStrictMode.defaultPolicy = FragmentStrictMode.Policy.Builder()
        .detectFragmentReuse()
        .detectFragmentTagUsage()
        .detectRetainInstanceUsage()
        .detectSetUserVisibleHint()
        .detectTargetFragmentUsage()
        .detectWrongFragmentContainer()
        .penaltyLog()
        .penaltyDeath()
        .build()
}