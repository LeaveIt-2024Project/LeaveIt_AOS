package com.example.leaveit.domain.usecase.place.searchPlace

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.StoreLogSearchKeywordRespoitory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreLogSearchKeywordUseCaseImpl @Inject constructor(
    private val bindStoreLogSearchKeywordRepositoryImpl : StoreLogSearchKeywordRespoitory
) : StoreLogSearchKeywordUseCase {
    override suspend fun excute(value: String): Flow<DataResource<Boolean>> {
      return bindStoreLogSearchKeywordRepositoryImpl.setLog(value)
    }
}