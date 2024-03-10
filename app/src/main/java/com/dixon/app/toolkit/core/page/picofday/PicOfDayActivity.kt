package com.dixon.app.toolkit.core.page.picofday

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dixon.app.toolkit.core.base.app.arch.ArchActivity
import com.dixon.app.toolkit.ui.theme.AppTheme
import com.dixon.app.toolkit.ui.theme.StatusCompatSurface

/**
 * 每日一图页面
 */
class PicOfDayActivity : ArchActivity<PicOfDayViewModel>(PicOfDayViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                StatusCompatSurface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black // 页面的背景色
                ) {
                    val picOfDayRes by viewModel.picOfDayLivaData.observeAsState(
                        // 初始显示的默认值
                        initial = Result.success(listOf())
                    )
                    PicOfDayUi(picOfDayResult = picOfDayRes)
                }
            }
        }
    }
}
