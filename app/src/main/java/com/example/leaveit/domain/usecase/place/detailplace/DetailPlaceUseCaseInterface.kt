package com.example.leaveit.domain.usecase.place.detailplace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailCultureDomainModel
import com.example.leaveit.domain.model.DetailFestivalDomainModel
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailPlaceUseCaseInterface {
    suspend fun getDetailPlaceData(
        id: String,
        type: String
    ): Flow<DataResource<DetailPlaceDomainModel>>

    suspend fun getDetailCultureData(
        id: String,
        type: String
    ): Flow<DataResource<DetailCultureDomainModel>>

    suspend fun getDetailFestivalData(
        id: String,
        type: String
    ): Flow<DataResource<DetailFestivalDomainModel>>
}