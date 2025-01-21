package com.example.leaveit.data.restraunt

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.RestrantListDomainModel
import com.example.leaveit.domain.repository.RestrauntRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RestrauntRepsitoryImpl @Inject constructor(
    val bindRestrauntDataSourceImpl: RestrauntDataSource
) : RestrauntRepository {
    override suspend fun getRestrauntData(
        longitude: String,
        latitude: String
    ): Flow<DataResource<RestrantListDomainModel>> = flow{
        emit(DataResource.Loading())
        try{
            val result = bindRestrauntDataSourceImpl.getRestrauntData(longitude,latitude)
            emit(DataResource.success(result.toDomain()))
        }catch (e : Exception){
            emit(DataResource.error(e))
        }
    }
}