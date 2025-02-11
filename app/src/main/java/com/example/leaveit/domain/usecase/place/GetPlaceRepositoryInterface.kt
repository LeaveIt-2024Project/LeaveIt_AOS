package com.example.leaveit.domain.usecase.place

import androidx.paging.PagingData
import com.example.leaveit.domain.model.PlaceDomainModel
import kotlinx.coroutines.flow.Flow

interface GetPlaceRepositoryInterface {
    suspend fun getAllPlaceData(value : String) : Flow<PagingData<PlaceDomainModel>>

    suspend fun getSortByRegionPlaceData(areadCode : String, contentTypeId : String) : Flow<PagingData<PlaceDomainModel>>

    suspend fun getSearchPlaceData(query : String) : Flow<PagingData<PlaceDomainModel>>
    //관광지 레포지 인터페이스
}