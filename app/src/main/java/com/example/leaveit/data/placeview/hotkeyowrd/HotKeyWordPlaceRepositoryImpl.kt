package com.example.leaveit.data.placeview.hotkeyowrd

import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.HotKeyWordPlaceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HotKeyWordPlaceRepositoryImpl @Inject constructor(
    private val bindHotKeyWordPlaceDataSource : HotKeyWordPlaceDataSource
) : HotKeyWordPlaceRepository{
    override suspend fun getHotKeyWordPlace(): Flow<DataResource<List<String>>> = flow{
        emit(DataResource.loading())
        try{
            val data = bindHotKeyWordPlaceDataSource.getHotKeyWordPlace()
            emit(DataResource.success(data))
        }catch (e : Exception){
            emit(DataResource.error(e))
        }
    }
}