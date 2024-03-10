package com.dixon.app.toolkit.core.page.translate

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dixon.app.toolkit.logic.model.TranslateResult
import com.dixon.app.toolkit.core.base.app.arch.ArchActivity
import com.dixon.app.toolkit.ui.theme.AppTheme
import com.dixon.app.toolkit.ui.theme.StatusCompatSurface

/**
 * 翻译页面
 */
class TranslateActivity : ArchActivity<TranslateViewModel>(TranslateViewModel::class.java) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                StatusCompatSurface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black // 页面的背景色
                ) {
                    val telAddressRes by viewModel.translateResLivaData.observeAsState(
                        // 初始显示的默认值
                        initial = Result.success(TranslateResult())
                    )
                    TranslateUi(
                        translateResult = telAddressRes,
                        onQueryChanged = { newTel ->
                            viewModel.translateText(newTel)
                        }
                    )
                }
            }
        }
    }
}
