package com.example.leaveit.domain.usecase.detail_restraunt

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailRestrauntDomainModel
import com.example.leaveit.domain.repository.DetailRestrauntRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRestrauntUseCaseImpl @Inject constructor(
    private val bindDetailRestrauntRespository : DetailRestrauntRepository
) : DetailRestrauntUseCase {
    override suspend fun getData(value : String): Flow<DataResource<DetailRestrauntDomainModel>> {
            return bindDetailRestrauntRespository.getDertailRestrauntData(value)
    }
}