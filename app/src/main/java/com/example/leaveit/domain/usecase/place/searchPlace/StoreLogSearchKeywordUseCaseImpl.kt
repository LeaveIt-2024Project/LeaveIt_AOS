package com.example.leaveit.domain.usecase.place.searchPlace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.StoreLogSearchKeywordRespoitory
import com.google.android.gms.fido.u2f.api.common.ResponseData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreLogSearchKeywordUseCaseImpl @Inject constructor(
    private val bindStoreLogSearchKeywordRepositoryImpl : StoreLogSearchKeywordRespoitory
) : StoreLogSearchKeywordUseCase {
    override suspend fun excute(value: String): Flow<DataResource<ResponseData>> {
      return bindStoreLogSearchKeywordRepositoryImpl.setLog(value)
    }
}