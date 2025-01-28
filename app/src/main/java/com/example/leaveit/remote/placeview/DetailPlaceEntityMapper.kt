package com.example.leaveit.remote.placeview

import com.example.leaveit.data.placeview.detailplace.DetailPlaceDataResourceDataModel

interface DetailPlaceResponseMapper {
    fun toDataSourceModel() : DetailPlaceDataResourceDataModel
}