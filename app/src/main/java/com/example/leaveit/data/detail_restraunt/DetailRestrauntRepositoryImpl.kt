package com.example.leaveit.data.detail_restraunt

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailRestrauntDomainModel
import com.example.leaveit.domain.repository.DetailRestrauntRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRestrauntRepositoryImpl @Inject constructor(
    private val bindDetailRestrauntDataSource : DetailRestrauntDataSource
) : DetailRestrauntRepository {
    override suspend fun getDertailRestrauntData(value: String): Flow<DataResource<DetailRestrauntDomainModel>> = flow {
        emit(DataResource.Loading())

        try{
            val data = bindDetailRestrauntDataSource.getDetailRestrauntData(value)
            emit(DataResource.success(data.toDomain()))


        }catch (e : Exception){
            emit(DataResource.error(e))
        }
    }
}