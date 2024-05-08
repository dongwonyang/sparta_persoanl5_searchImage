package com.example.personal5_search_image.network

import com.example.personal5_search_image.network.data.remote.SearchRemoteDataResource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dapi.kakao.com"

    private val okHttpClient by lazy{
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val search: SearchRemoteDataResource by lazy {
        retrofit.create(SearchRemoteDataResource::class.java)
    }
}