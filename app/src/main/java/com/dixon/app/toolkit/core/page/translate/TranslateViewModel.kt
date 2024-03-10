package com.dixon.app.toolkit.core.page.translate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.dixon.app.toolkit.logic.AppRepository

class TranslateViewModel : ViewModel() {

    private val textLiveData = MutableLiveData<String>()

    // 查询关键词的变更会自动触发接口的调用
    fun translateText(text: String) {
        textLiveData.value = text
    }

    // 真正被UI用到的LiveData
    val translateResLivaData = textLiveData.switchMap { query ->
        AppRepository.translate(query)
    }
}