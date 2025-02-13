package com.example.leaveit.data.placeview.searchPlace

import com.google.android.gms.fido.u2f.api.common.ResponseData

interface StoreLogSearchKeywordDataSourceInterface {

    suspend fun setLog(value : String) : ResponseData
}