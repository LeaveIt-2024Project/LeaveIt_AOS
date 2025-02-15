package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import kotlinx.coroutines.flow.Flow

interface HotKeyWordPlaceRepository {
    suspend fun getHotKeyWordPlace() : Flow<DataResource<List<String>>>
}