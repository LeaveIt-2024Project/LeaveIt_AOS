package com.example.leaveit.data.placeview.detailplace

import com.example.leaveit.data.model.DetailCultureDataModel
import com.example.leaveit.data.model.DetailFestivalDataModel
import com.example.leaveit.data.model.DetailPlaceDataDataModel

interface DetailPlaceDataSourceInterface {

    suspend fun getDetailPlaceInfo(id: String,type : String) : DetailPlaceDataDataModel

    suspend fun getDetailCulturePlaceInfo(id : String, type : String) : DetailCultureDataModel

    suspend fun getDetailFestivalInfo(id: String,type : String) : DetailFestivalDataModel
}