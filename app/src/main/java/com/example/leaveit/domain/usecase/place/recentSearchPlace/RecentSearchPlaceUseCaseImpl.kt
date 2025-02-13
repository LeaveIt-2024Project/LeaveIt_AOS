package com.example.leaveit.domain.usecase.place.recentSearchPlace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.RecentSearchPlaceRepository
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecentSearchPlaceUseCaseImpl @Inject constructor(
    private val bindRecentSearchPlaceRepositoryImpl: RecentSearchPlaceRepository
) : RecentSearchPlaceUseCase {
    override suspend fun getData(): Flow<DataResource<List<RecentSearchPlaceEntity>>> {
        return bindRecentSearchPlaceRepositoryImpl.getData()
    }

    override suspend fun setData(value: RecentSearchPlaceEntity): Flow<DataResource<Unit>> {
        return bindRecentSearchPlaceRepositoryImpl.setData(value)
    }

    override suspend fun deleteData(value: String): Flow<DataResource<Unit>> {
        return bindRecentSearchPlaceRepositoryImpl.deleteData(value)
    }

    override suspend fun deleteAllData(): Flow<DataResource<Unit>> {
        return bindRecentSearchPlaceRepositoryImpl.deleteAllData()
    }

}
