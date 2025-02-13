package com.example.leaveit.local.RecentSearch

import com.example.leaveit.data.recent_search.RecentSearchDataSource
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.local.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecentSearchDataSourceImpl @Inject constructor(
    private val service: AppDatabase
) : RecentSearchDataSource {
    override suspend fun getAllData(): Flow<DataResource<List<RecentSearchEntity>>> = flow {
        emit(DataResource.loading())
        try {
            val result = service.appData().getAll()
            emit(DataResource.success(result!!))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    override suspend fun addTuple(data: RecentSearchEntity): Flow<DataResource<Boolean>> = flow {
        emit(DataResource.loading())
        try {
            val maxOrderIndex = service.appData().getMaxOrderIndex() ?: 0
            data.searchIndex = maxOrderIndex + 1
            service.appData().insertData(data)
            emit(DataResource.success(true))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    override suspend fun deleteTuple(): Flow<DataResource<List<RecentSearchEntity>>> = flow {
        emit(DataResource.loading())
        try {
            service.appData().deleteFirstRow()
            service.appData().rearrangeAfterFirstDeletion()
            val data = service.appData().getAll()
            emit(DataResource.success(data!!))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    override suspend fun getMaxIndexCount(): Flow<DataResource<Int>> = flow {
        emit(DataResource.loading())
        try {
            val result = service.appData().getMaxOrderIndex()
            emit(DataResource.success(result!!))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    override suspend fun deleteAllData(): Flow<DataResource<Boolean>> = flow {
        emit(DataResource.loading())
        try {
            service.appData().deleteAllData()
            emit(DataResource.success(true))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }

    }
}