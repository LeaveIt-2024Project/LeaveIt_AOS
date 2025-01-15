package com.example.leaveit.data.navigate

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.ReverseGeoCodingDomainModel
import com.example.leaveit.domain.repository.ReverseGeoCodingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReverseGeoCodingRepsitoryImpl @Inject constructor(
    private val bindReverseGeoCodingDataSource : ReverseGeoCodingDataSourceInterface
) : ReverseGeoCodingRepository{
    override suspend fun getReverseGeoData(value: String): Flow<DataResource<ReverseGeoCodingDomainModel>> = flow {
       emit(DataResource.loading())
        try{
            val result = bindReverseGeoCodingDataSource.getTranslateData(value).toDataModel()
            emit(DataResource.success(result.toDomain()))
        }catch (e : Exception){
            emit(DataResource.error(e))
        }

    }
}
