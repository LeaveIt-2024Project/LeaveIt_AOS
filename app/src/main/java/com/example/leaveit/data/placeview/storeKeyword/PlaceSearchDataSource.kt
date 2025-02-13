package com.example.leaveit.data.placeview.storeKeyword

import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity

interface PlaceSearchDataSource {
    suspend fun getData() : List<RecentSearchPlaceEntity>
    suspend fun setData(value : RecentSearchPlaceEntity) : Unit
    suspend fun deleteData(value : String)  : Unit
    suspend fun deleteAllData() : Unit
}