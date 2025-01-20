package com.example.leaveit.data.review.detailplace

interface DetailPlaceDataSourceInterface {

    suspend fun getDetailPlaceInfo(value : String) : DetailPlaceDataResourceDataModel
}