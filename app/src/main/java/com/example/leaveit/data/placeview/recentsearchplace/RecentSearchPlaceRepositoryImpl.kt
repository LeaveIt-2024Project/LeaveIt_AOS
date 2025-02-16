package com.example.leaveit.data.placeview.recentsearchplace

import com.example.leaveit.data.placeview.storeKeyword.PlaceSearchDataSource
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.RecentSearchPlaceRepository
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RecentSearchPlaceRepositoryImpl @Inject constructor(
    private val bindPlaceSearchDataSourceImpl: PlaceSearchDataSource
) : RecentSearchPlaceRepository {
    override suspend fun getData(): Flow<DataResource<List<RecentSearchPlaceEntity>>> = flow {
        emit(DataResource.loading())
        try {
            val data = bindPlaceSearchDataSourceImpl.getData()
            emit(DataResource.success(data))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }


    }

    override suspend fun setData(value: RecentSearchPlaceEntity): Flow<DataResource<Unit>> = flow {
        emit(DataResource.loading())
        try {
            val data = bindPlaceSearchDataSourceImpl.setData(value)
            emit(DataResource.success(data))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    override suspend fun deleteData(value: String): Flow<DataResource<Unit>> = flow {
        emit(DataResource.loading())
        try {
            val data = bindPlaceSearchDataSourceImpl.deleteData(value)
            emit(DataResource.success(data))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }

    override suspend fun deleteAllData(): Flow<DataResource<Unit>> = flow {
        emit(DataResource.loading())
        try {
            val data = bindPlaceSearchDataSourceImpl.deleteAllData()
            emit(DataResource.success(data))
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }
}