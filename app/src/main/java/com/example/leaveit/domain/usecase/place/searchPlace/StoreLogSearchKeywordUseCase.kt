package com.example.leaveit.domain.usecase.place.searchPlace

import com.example.leaveit.dataResource.DataResource
import com.google.android.gms.fido.u2f.api.common.ResponseData
import kotlinx.coroutines.flow.Flow

interface StoreLogSearchKeywordUseCase {
    suspend fun excute(value : String) : Flow<DataResource<ResponseData>>
}