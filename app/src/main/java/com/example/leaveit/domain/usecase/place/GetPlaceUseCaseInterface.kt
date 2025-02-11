package com.example.leaveit.domain.usecase.place

import androidx.paging.PagingData
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel
import kotlinx.coroutines.flow.Flow

interface GetPlaceUseCaseInterface {
    suspend fun getSortByRegionUseCase(contentTypedId : String, areaCode : String) : Flow<PagingData<SelectRegionModel>>

    suspend fun getAllPlaceUseCase(contentTypedId : String) : Flow<PagingData<SelectRegionModel>>

}