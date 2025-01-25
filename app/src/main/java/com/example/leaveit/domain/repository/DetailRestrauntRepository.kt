package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailRestrauntDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailRestrauntRepository {
    suspend fun getDertailRestrauntData(value : String) : Flow<DataResource<DetailRestrauntDomainModel>>
}