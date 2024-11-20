package com.example.leaveit.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService{
    private const val BASE_URL  = "https://api.github.com/"

    val retrofit: Retrofit = Retrofit.Builder() //레트로핏 객체 선언
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}