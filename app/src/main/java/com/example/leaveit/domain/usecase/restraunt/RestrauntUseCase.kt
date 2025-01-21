package com.example.leaveit.domain.usecase.restraunt

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.RestrantListDomainModel
import kotlinx.coroutines.flow.Flow

interface RestrauntUseCase {
    suspend fun getRestrauntData(
        longitude: String,
        latitude: String
    ): Flow<DataResource<RestrantListDomainModel>>
}