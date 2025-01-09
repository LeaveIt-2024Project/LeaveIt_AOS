package com.example.leaveit.remote.placeview

import com.example.leaveit.data.review.detailplace.DetailPlaceDataResourceDataModel

interface DetailPlaceResponseMapper {
    fun toDataSourceModel() : DetailPlaceDataResourceDataModel
}