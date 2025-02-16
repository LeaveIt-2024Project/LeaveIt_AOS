package com.example.leaveit.data.placeview.searchPlace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.StoreLogSearchKeywordRespoitory
import com.google.android.gms.fido.u2f.api.common.ResponseData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StoreLogSearchKeywordRepositoryImpl @Inject constructor(
    private val bindStoreLogSearchKeywordDataSourceImpl: StoreLogSearchKeywordDataSourceInterface
) : StoreLogSearchKeywordRespoitory {

    override suspend fun setLog(value: String): Flow<DataResource<Boolean>> = flow {
        emit(DataResource.Loading())
        try {
            val data = bindStoreLogSearchKeywordDataSourceImpl.setLog(value)
            if(data.isSuccessful){
                emit(DataResource.success(true))
            }
        } catch (e: Exception) {
            emit(DataResource.error(e))
        }
    }
}