package com.example.leaveit.data.placeview.searchPlace

import retrofit2.Response

interface StoreLogSearchKeywordDataSourceInterface {

    suspend fun setLog(value : String) : Response<Unit>
}