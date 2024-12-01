package com.example.leaveit.domain.usecase

import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModelList

interface GetPlaceUseCaseInterface {
    suspend fun getPlaceUseCase(contentTypedId : String, areaCode : String) : SelectRegionModelList

}