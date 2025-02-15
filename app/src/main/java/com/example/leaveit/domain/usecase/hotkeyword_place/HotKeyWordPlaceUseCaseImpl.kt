package com.example.leaveit.domain.usecase.hotkeyword_place

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.HotKeyWordPlaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HotKeyWordPlaceUseCaseImpl @Inject constructor(
    private val bindHotKeyWordPlaceRepositoryImpl : HotKeyWordPlaceRepository
) : HotKeyWordPlaceUseCase {
    override suspend fun getHotKeyWord(): Flow<DataResource<List<String>>> {
        return bindHotKeyWordPlaceRepositoryImpl.getHotKeyWordPlace()
    }
}