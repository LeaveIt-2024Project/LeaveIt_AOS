package com.example.leaveit.domain.usecase.restraunt

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.RestrantListDomainModel
import com.example.leaveit.domain.repository.RestrauntRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestrauntUseCaseImpl @Inject constructor(
    val bindRestrauntRepsitoryImpl: RestrauntRepository
) : RestrauntUseCase {
    override suspend fun getRestrauntData(
        longitude: String,
        latitude: String
    ): Flow<DataResource<RestrantListDomainModel>> {
        return bindRestrauntRepsitoryImpl.getRestrauntData(longitude,latitude)
    }

}