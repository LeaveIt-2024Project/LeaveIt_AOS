package com.example.leaveit.domain.repository

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.PathDomainModel
import kotlinx.coroutines.flow.Flow

interface PathRepository {
    suspend fun getPath(start : String, goal : String) : Flow<DataResource<PathDomainModel>>
}