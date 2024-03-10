package com.dixon.app.toolkit.logic.network

import com.dixon.app.toolkit.logic.model.PicOfDayResponse
import com.dixon.app.toolkit.logic.model.TranslateResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {

    // QQ免费翻译接口
    @GET("api/txt/QQFanyi?")
    fun translate(@Query("sourceText") text: String): Call<TranslateResponse>

    // 每日一图
    @GET("api/bing")
    fun picOfDay(): Call<PicOfDayResponse>
}