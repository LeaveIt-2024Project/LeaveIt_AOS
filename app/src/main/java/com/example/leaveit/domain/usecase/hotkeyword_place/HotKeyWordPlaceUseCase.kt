package com.example.leaveit.domain.usecase.hotkeyword_place

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.HotKeyWordDomainModel
import kotlinx.coroutines.flow.Flow

interface HotKeyWordPlaceUseCase {
    suspend fun getHotKeyWord() : Flow<DataResource<HotKeyWordDomainModel>>
}