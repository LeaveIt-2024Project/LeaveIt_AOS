package com.example.leaveit.remote

import com.example.leaveit.data.model.PlaceViewDataModelList
import com.example.leaveit.remote.entity.response

internal interface RemoteMapper<PlaceDataModel>{
    suspend fun toData(temp: response): PlaceViewDataModelList
}