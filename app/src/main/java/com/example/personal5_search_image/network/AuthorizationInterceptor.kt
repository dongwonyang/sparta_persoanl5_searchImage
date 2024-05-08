package com.example.personal5_search_image.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val REST_API_KEY = "d6fd4a43781241e9ca80ad0218dbc78a"
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK ${REST_API_KEY}"
            )
            .build()

        return chain.proceed(newRequest)
    }
}