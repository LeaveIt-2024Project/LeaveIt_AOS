package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.HotKeyWordDomainModel
import kotlinx.coroutines.flow.Flow

interface HotKeyWordPlaceRepository {
    suspend fun getHotKeyWordPlace() : Flow<DataResource<HotKeyWordDomainModel>>
}