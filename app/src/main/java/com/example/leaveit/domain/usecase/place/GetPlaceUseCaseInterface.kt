package com.example.leaveit.domain.usecase.place

import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModelList

interface GetPlaceUseCaseInterface {
    suspend fun getSortByRegionUseCase(contentTypedId : String, areaCode : String) : SelectRegionModelList

    suspend fun getAllPlaceUseCase(contentTypedId : String) : SelectRegionModelList

}