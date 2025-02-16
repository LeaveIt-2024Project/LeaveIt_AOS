package com.example.leaveit.local.PlaceSearch

import com.example.leaveit.data.placeview.storeKeyword.PlaceSearchDataSource
import com.example.leaveit.local.AppDatabase
import javax.inject.Inject

class RecentSearchPlaceDataSourceImpl @Inject constructor(
    private val service: AppDatabase
) : PlaceSearchDataSource {

    override suspend fun getData(): List<RecentSearchPlaceEntity> {
        return service.placeSearchData().getAll()
    }

    override suspend fun setData(value: RecentSearchPlaceEntity): Unit {
        return service.placeSearchData().insertData(value)
    }

    override suspend fun deleteData(value: String): Unit {
        return service.placeSearchData().deleteFirstRow(value)
    }

    override suspend fun deleteAllData(): Unit {
        return service.placeSearchData().deleteAllData()
    }
}