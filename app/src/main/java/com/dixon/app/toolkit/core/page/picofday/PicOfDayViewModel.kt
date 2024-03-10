package com.dixon.app.toolkit.core.page.picofday

import androidx.lifecycle.ViewModel
import com.dixon.app.toolkit.logic.AppRepository

class PicOfDayViewModel : ViewModel() {

    // 每日一图数据 不存在动态更新问题
    val picOfDayLivaData = AppRepository.picOfDay()
}