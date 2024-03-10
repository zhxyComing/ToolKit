package com.dixon.app.toolkit.logic

import androidx.lifecycle.liveData
import com.dixon.app.toolkit.logic.network.AppNetwork
import kotlinx.coroutines.Dispatchers

/**
 * Model 层入口，负责从网络或数据库获取数据模型
 */
object AppRepository {

    /**
     * 翻译
     */
    fun translate(text: String) = liveData(Dispatchers.IO) {
        val result = try {
            val response = AppNetwork.translate(text)
            if (response.code == 200) {
                Result.success(response.result)
            } else {
                Result.failure(RuntimeException("response status is ${response.code}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    /**
     * 每日一图
     */
    fun picOfDay() = liveData(Dispatchers.IO) {
        val result = try {
            val response = AppNetwork.picOfDay()
            if (response.code == 200) {
                Result.success(response.result)
            } else {
                Result.failure(RuntimeException("response status is ${response.code}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }
}