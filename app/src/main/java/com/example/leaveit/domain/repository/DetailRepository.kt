package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getDetailData(value : String) : Flow<DataResource<DetailPlaceDomainModel>>
}