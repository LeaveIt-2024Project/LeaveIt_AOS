package com.example.leaveit.remote.api.search

import com.example.leaveit.remote.entity.SearchEntity
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchApi {
    @Headers(
        "X-Naver-Client-Id: oZhud6tsY73L1LBn4rKH",
        "X-Naver-Client-Secret: cbPAM9ACV1"
    )
    @GET("v1/search/local.json")
    suspend fun getPreferRegions(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
        @Query("sort") sort: String
    ): SearchEntity
}