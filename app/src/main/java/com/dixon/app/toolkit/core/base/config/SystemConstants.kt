package com.dixon.app.toolkit.core.base.config

import com.dixon.app.toolkit.core.base.app.ContextAssistant
import com.dixon.app.toolkit.core.util.px2dp

/**
 * 系统常量
 */

val SYS_STATUS_BAR_DP: Int by lazy {
    val context = ContextAssistant.application()
    // 获得状态栏高度
    val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
    val barSizePixel = context.resources.getDimensionPixelSize(resourceId)
    barSizePixel.px2dp(context)
}