package com.dixon.app.toolkit.core.page.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dixon.app.toolkit.core.base.app.arch.ArchActivity
import com.dixon.app.toolkit.core.page.PageType
import com.dixon.app.toolkit.core.util.jumpPage
import com.dixon.app.toolkit.ui.theme.AppTheme
import com.dixon.app.toolkit.ui.theme.StatusCompatSurface

/**
 * 首页
 */
class HomeActivity : ArchActivity<HomeViewModel>(HomeViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                StatusCompatSurface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black // 页面的背景色
                ) {
                    HomeUi(onFunCardClick = { funType ->
                        when (funType) {
                            FunType.TRANSLATE -> jumpPage(PageType.TRANSLATE)
                            FunType.CALCULATE -> jumpPage(PageType.TRANSLATE)
                            FunType.NOTE -> jumpPage(PageType.TRANSLATE)
                            FunType.PIC_OF_DAY -> jumpPage(PageType.PIC_OF_DAY)
                        }
                    })
                }
            }
        }
    }
}