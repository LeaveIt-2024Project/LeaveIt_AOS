package com.example.leaveit.presentation.placeview.place.showplaceview

import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import com.example.leaveit.presentation.placeview.place.showplaceview.DTO.CategoryDto

interface ShowPlaceModelMapper {
    suspend fun toSearchModel(): CategoryDto
    suspend fun toPlaceModel() : SelectRegionModel
}