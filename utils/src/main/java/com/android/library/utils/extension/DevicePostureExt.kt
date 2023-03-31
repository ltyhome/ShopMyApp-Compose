package com.android.library.utils.extension

import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * [FoldingFeature]用来描述屏幕的折叠状态或两个物理显示面板之间的铰链状态
 */
sealed interface DevicePosture {
    object NormalPosture : DevicePosture

    data class BookPosture(
        val hingePosition: Rect
    ) : DevicePosture

    data class Separating(
        val hingePosition: Rect,
        var orientation: FoldingFeature.Orientation
    ) : DevicePosture
}

/**
 * 折叠状态
 * [FoldingFeature.State.HALF_OPENED]（半开）
 */
@OptIn(ExperimentalContracts::class)
fun FoldingFeature?.isBookPosture(): Boolean {
    contract { returns(true) implies (this@isBookPosture != null) }
    return this?.state == FoldingFeature.State.HALF_OPENED &&
            this.orientation == FoldingFeature.Orientation.VERTICAL
}

/**
 * [FoldingFeature.State.FLAT]（展开）
 * 内容区域是否分割为2块。 true：半开或双屏设备；false：计算是否有遮挡
 */
@OptIn(ExperimentalContracts::class)
fun FoldingFeature?.isSeparating(): Boolean {
    contract { returns(true) implies (this@isSeparating != null) }
    return this?.state == FoldingFeature.State.FLAT && this.isSeparating
}

/**
 * 导航导轨、导航抽屉内导航内容的不同位置取决于设备大小和状态
 * [TOP] 顶部
 * [CENTER] 居中
 */
enum class AppNavigationContentPosition {
    TOP, CENTER
}

/**
 * 导航类型
 * [BOTTOM_NAVIGATION] 底部导航栏
 * [NAVIGATION_RAIL]垂直导航栏
 * [PERMANENT_NAVIGATION_DRAWER] 侧边抽屉导航栏
 */
enum class AppNavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * App内容根据屏幕尺寸和状态的展示方式
 * [SINGLE_PANE] 一屏
 * [DUAL_PANE] 二分屏
 */
enum class AppContentType {
    SINGLE_PANE, DUAL_PANE
}