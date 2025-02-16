package com.example.leaveit.remote.search_place

import com.example.leaveit.data.placeview.searchPlace.StoreLogSearchKeywordDataSourceInterface
import com.example.leaveit.remote.api.place.PlaceApi
import com.example.leaveit.remote.entity.SearchBody
import retrofit2.Response
import javax.inject.Inject

class StoreLogSearchKeywordDataSourceImpl @Inject constructor(
    val service: PlaceApi
) : StoreLogSearchKeywordDataSourceInterface {
    override suspend fun setLog(value: String):Response<Unit> {
        return service.setSearchLog(SearchBody(title = value))
    }
}