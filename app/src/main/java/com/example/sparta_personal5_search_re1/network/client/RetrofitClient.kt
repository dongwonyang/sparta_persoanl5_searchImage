package com.example.sparta_personal5_search_re1.network.client

import com.example.sparta_personal5_search_re1.network.data.remote.SearchRemoteDataResource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private val baseUrl:String = "https://dapi.kakao.com"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val search: SearchRemoteDataResource by lazy{
        retrofit.create(SearchRemoteDataResource::class.java)
    }
}