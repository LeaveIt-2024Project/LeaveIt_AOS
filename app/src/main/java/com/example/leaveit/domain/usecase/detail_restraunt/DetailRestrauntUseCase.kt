package com.example.leaveit.domain.usecase.detail_restraunt

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailRestrauntDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailRestrauntUseCase {
    suspend fun getData(value : String) : Flow<DataResource<DetailRestrauntDomainModel>>
}