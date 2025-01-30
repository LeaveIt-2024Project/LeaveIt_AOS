package com.example.leaveit.remote.placeview

import com.example.leaveit.data.model.DetailPlaceDataDataModel

interface DetailPlaceResponseMapper {
    fun toPlaceDataSourceModel(): DetailPlaceDataDataModel
}