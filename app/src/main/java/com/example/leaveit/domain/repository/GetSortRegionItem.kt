package com.example.leaveit.domain.repository

import com.example.leaveit.remote.entity.PlaceEntitiy
import kotlinx.coroutines.flow.Flow

interface getSortRegionItem{
    fun getSortRegionItemFlow(
        serviceKey : String,
        contentTypedId : String,
        areaCode : String
    ) : Flow<List<PlaceEntitiy>>
}