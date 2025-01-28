package com.example.leaveit.data.placeview.detailplace

interface DetailPlaceDataSourceInterface {

    suspend fun getDetailPlaceInfo(value : String) : DetailPlaceDataResourceDataModel
}