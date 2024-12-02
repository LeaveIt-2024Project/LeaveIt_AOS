package com.example.leaveit.domain.usecase

import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModelList

interface GetPlaceUseCaseInterface {
    suspend fun getSortByRegionUseCase(contentTypedId : String, areaCode : String) : SelectRegionModelList

    suspend fun getAllPlaceUseCase(contentTypedId : String) : SelectRegionModelList

}