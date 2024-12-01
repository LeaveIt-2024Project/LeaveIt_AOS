package com.example.leaveit.data.placeview

import com.example.leaveit.data.model.PlaceViewDataModelList


interface PlaceViewDataSourceInterface {

    suspend fun getPlaceView(contentTypedId : String, areaCode : String): PlaceViewDataModelList
}