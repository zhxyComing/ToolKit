package com.dixon.app.toolkit.core.base.app

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import com.dixon.app.toolkit.R
import com.dixon.app.toolkit.core.util.Ln
import com.dixon.app.toolkit.core.util.StatusBarUtil

/**
 * 沉浸式状态栏
 * 主要是为了兼容老版本（XML 布局方式），如果用的是 Jetpack Compose 则不生效
 */
open class StatusBarCompatActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 确保状态栏图标颜色正常
            val decorView = window.decorView
            val wic = WindowInsetsControllerCompat(window, decorView)
            wic.isAppearanceLightStatusBars = true
            if (useStatusTransparent()) {
                Ln.i("StatusBar", "path A")
                StatusBarUtil.setColorForStatus(this)
            } else {
                Ln.i("StatusBar", "path B")
                window.statusBarColor = resources.getColor(statusBarColor(), null)
            }
        } else {
            Ln.i("StatusBar", "path C")
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getWindow().decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        // 隐藏底部横条 navigation bar
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
        window.navigationBarColor = Color.TRANSPARENT
    }

    open fun statusBarColor(): Int = R.color.app_background_color_light

    // 透明沉浸式状态栏 状态栏不再占空间 同时颜色也变成半透明
    open fun useStatusTransparent(): Boolean = false
}