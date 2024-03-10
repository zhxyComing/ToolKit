package com.dixon.app.toolkit.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object AppNetwork {

    private val appService = ServiceCreator.create<AppService>()

    suspend fun translate(text: String) = appService.translate(text).await()

    suspend fun picOfDay() = appService.picOfDay().await()

    private suspend fun <T> Call<T>.await(): T = suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (body != null) continuation.resume(body)
                else continuation.resumeWithException(RuntimeException("response body is null"))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resumeWithException(t)
            }
        })
    }
}