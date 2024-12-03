package com.example.leaveit.remote.placeview

import com.example.leaveit.data.model.PlaceViewDataModelList
import com.example.leaveit.remote.entity.response

internal interface PlaceEntityMapper<PlaceDataModel> {
    suspend fun toData(temp: response): PlaceViewDataModelList
}