package com.example.core_data.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = buildRequestWithAccessToken(originalRequest)
        return chain.proceed(request)
    }

    private fun buildRequestWithAccessToken(request: Request): Request {
        return request.newBuilder()
            .addHeader("X-API-KEY", API_KEY)
            .addHeader("type", "TOP_100_POPULAR_FILMS")
            .build()
    }

    companion object {
        private const val API_KEY = "a3c8017e-5b20-4cc1-ba9a-d77c7a182501"
    }

}