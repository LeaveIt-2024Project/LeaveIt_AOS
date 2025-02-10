package com.example.leaveit.domain.usecase.place

import androidx.paging.PagingData
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModelList
import kotlinx.coroutines.flow.Flow

interface GetPlaceUseCaseInterface {
    suspend fun getSortByRegionUseCase(contentTypedId : String, areaCode : String) : SelectRegionModelList

    suspend fun getAllPlaceUseCase(contentTypedId : String) : Flow<PagingData<SelectRegionModel>>

}