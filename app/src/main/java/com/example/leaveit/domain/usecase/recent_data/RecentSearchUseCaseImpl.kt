package com.example.leaveit.domain.usecase.recent_data

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.RecentSearchRepository
import com.example.leaveit.local.RecentSearch.RecentSearchEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentSearchUseCaseImpl @Inject constructor(
    private val bindRecentSearchRespositoryImpl : RecentSearchRepository
) : RecentSearchUseCase {
    override suspend fun getAllData(): Flow<DataResource<List<RecentSearchEntity>>> {
        return bindRecentSearchRespositoryImpl.getAllData()
    }

    override suspend fun addTuple(data: RecentSearchEntity): Flow<DataResource<Boolean>> {
       return bindRecentSearchRespositoryImpl.addTuple(data)
    }

    override suspend fun deleteTuple(): Flow<DataResource<List<RecentSearchEntity>>> {
       return bindRecentSearchRespositoryImpl.deleteTuple()
    }

    override suspend fun getMaxIndexCount(): Flow<DataResource<Int>> {
        return bindRecentSearchRespositoryImpl.getMaxIndexCount()
    }

    override suspend fun deleteAllData(): Flow<DataResource<Boolean>> {
        return bindRecentSearchRespositoryImpl.deleteAllData()
    }
}