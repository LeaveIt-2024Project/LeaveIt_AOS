package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity
import kotlinx.coroutines.flow.Flow

interface RecentSearchPlaceRepository {
    suspend fun getData(): Flow<DataResource<List<RecentSearchPlaceEntity>>>
    suspend fun setData(value: RecentSearchPlaceEntity): Flow<DataResource<Unit>>
    suspend fun deleteData(value: String): Flow<DataResource<Unit>>
    suspend fun deleteAllData(): Flow<DataResource<Unit>>
}