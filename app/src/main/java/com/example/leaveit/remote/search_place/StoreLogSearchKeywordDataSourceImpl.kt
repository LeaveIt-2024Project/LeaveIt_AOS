package com.example.leaveit.remote.search_place

import com.example.leaveit.data.placeview.searchPlace.StoreLogSearchKeywordDataSourceInterface
import com.example.leaveit.remote.api.place.PlaceApi
import com.google.android.gms.fido.u2f.api.common.ResponseData
import javax.inject.Inject

class StoreLogSearchKeywordDataSourceImpl @Inject constructor(
    val service: PlaceApi
) : StoreLogSearchKeywordDataSourceInterface {
    override suspend fun setLog(value: String):ResponseData {
        return service.setSearchLog(value)
    }
}