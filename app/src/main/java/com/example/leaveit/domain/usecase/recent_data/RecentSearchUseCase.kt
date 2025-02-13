package com.example.leaveit.domain.usecase.recent_data

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity
import com.example.leaveit.local.RecentSearch.RecentSearchEntity
import kotlinx.coroutines.flow.Flow

interface RecentSearchUseCase {

    suspend fun getAllData() : Flow<DataResource<List<RecentSearchPlaceEntity>>>

    suspend fun addTuple(data: RecentSearchEntity): Flow<DataResource<Boolean>>

    suspend fun deleteTuple(): Flow<DataResource<List<RecentSearchEntity>>>

    suspend fun getMaxIndexCount(): Flow<DataResource<Int>>

    suspend fun deleteAllData() : Flow<DataResource<Boolean>>
}