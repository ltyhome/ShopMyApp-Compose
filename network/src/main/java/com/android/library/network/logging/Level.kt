package com.android.library.network.logging

internal class Level(val code: Int) {
    companion object {
        /**
         * No logs.
         */
        val NONE = Level(0)

        /**
         * - URL
         * - Method
         * - Headers
         * - Body
         */
        val BASIC = Level(1)

        /**
         * - URL
         * - Method
         * - Headers
         */
        val HEADERS = Level(2)

        /**
         * - URL
         * - Method
         * - Body
         */
        val BODY = Level(3)
    }
}