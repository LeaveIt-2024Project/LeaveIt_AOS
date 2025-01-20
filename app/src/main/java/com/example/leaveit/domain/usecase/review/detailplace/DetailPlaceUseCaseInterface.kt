package com.example.leaveit.domain.usecase.review.detailplace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailPlaceUseCaseInterface {
    suspend fun getDetailPlaceData(value : String) : Flow<DataResource<DetailPlaceDomainModel>>
}