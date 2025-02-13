package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.google.android.gms.fido.u2f.api.common.ResponseData
import kotlinx.coroutines.flow.Flow

interface StoreLogSearchKeywordRespoitory {

    suspend fun setLog(value : String) : Flow<DataResource<ResponseData>>
}