package com.example.leaveit.remote.api.review

import com.example.leaveit.BuildConfig
import com.example.leaveit.remote.entity.detailPlaceRootResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailPlaceApi {

    @GET("detailIntro1")
    suspend fun getDetailPlaceInfo(
        @Query("MobileOS") mobileOs : String = "AND",
        @Query("MobileApp") mobileApp : String = "Leaveit",
        @Query("serviceKey") serviceKey : String = BuildConfig.TOUR_API_KEY,
        @Query("contentTypeId") contentTypedId : String = "12",
        @Query("contentId") contentId : String,
        @Query("_type") type : String = "json"
    ) : detailPlaceRootResponse
}