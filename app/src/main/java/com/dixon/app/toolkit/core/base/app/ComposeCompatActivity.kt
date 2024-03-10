package com.dixon.app.toolkit.core.base.app

import android.os.Bundle
import androidx.core.view.WindowCompat

/**
 * 为 Jetpack Compose 定制的兼容 Activity
 * 目前主要是为了实现沉浸式状态栏
 */
open class ComposeCompatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 决定了状态栏是否和内容重叠
        // 根据后续设计稿决定是否打开
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}