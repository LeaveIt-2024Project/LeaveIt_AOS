package com.example.leaveit.data.navigate

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.PathDomainModel
import com.example.leaveit.domain.repository.PathRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PathRepositoryImpl @Inject constructor(
    val bindPathDataSource : PathDataSourceInterface
): PathRepository {
    override suspend fun getPath(start: String, goal: String): Flow<DataResource<PathDomainModel>> = flow {
        emit(DataResource.Loading())
        try{
            val result = bindPathDataSource.getPath(start,goal).toDomain()
            emit(DataResource.success(result))
        }catch (e : Exception){
            emit(DataResource.error(e))
        }
    }

}