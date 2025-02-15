package com.example.leaveit.domain.usecase.hotkeyword_place

import com.example.leaveit.dataResource.DataResource
import kotlinx.coroutines.flow.Flow

interface HotKeyWordPlaceUseCase {
    suspend fun getHotKeyWord() : Flow<DataResource<List<String>>>
}