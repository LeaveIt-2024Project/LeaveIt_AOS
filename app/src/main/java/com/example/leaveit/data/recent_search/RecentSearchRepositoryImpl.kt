package com.example.leaveit.data.recent_search

import android.util.Log
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.repository.RecentSearchRepository
import com.example.leaveit.local.RecentSearchEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class RecentSearchRepositoryImpl @Inject constructor() : RecentSearchRepository {

    @Inject
    lateinit var bindRecentSearchDataSourceImpl: RecentSearchDataSource
    override suspend fun getAllData(): Flow<DataResource<List<RecentSearchEntity>>> {
        bindRecentSearchDataSourceImpl.getAllData().collectLatest { dataState ->
            when (dataState) {
                is DataResource.Error -> {
                    Log.e(TAG, dataState.throwable.message.toString())
                }

                is DataResource.Loading -> {
                    Log.d(TAG, "데이터 불러오는 중")
                }

                is DataResource.Success -> {
                    Log.d(TAG, "데이터 불러오기 성공")
                }
            }
        }
        return bindRecentSearchDataSourceImpl.getAllData()
    }

    override suspend fun addTuple(data: RecentSearchEntity): Flow<DataResource<Boolean>> {
        bindRecentSearchDataSourceImpl.addTuple(data).collectLatest { dataState ->
            when (dataState) {
                is DataResource.Error -> {
                    Log.e(TAG, dataState.throwable.message.toString())
                }

                is DataResource.Loading -> {
                    Log.d(TAG, "튜플 추가중")
                }

                is DataResource.Success -> {
                    Log.d(TAG, "튜플 데이터 추가 성공")
                }
            }
        }
        return bindRecentSearchDataSourceImpl.addTuple(data)
    }

    override suspend fun deleteTuple(): Flow<DataResource<List<RecentSearchEntity>>> {
        bindRecentSearchDataSourceImpl.deleteTuple().collect { dataState ->
            when (dataState) {
                is DataResource.Error -> {
                    Log.e(TAG, dataState.throwable.message.toString())
                }

                is DataResource.Loading -> {
                    Log.d(TAG, "튜플 삭제중")
                }

                is DataResource.Success -> {
                    Log.d(TAG, "튜플 삭제 성공")
                }
            }
        }
        return bindRecentSearchDataSourceImpl.deleteTuple()
    }

    override suspend fun getMaxIndexCount(): Flow<DataResource<Int>> {
        bindRecentSearchDataSourceImpl.getMaxIndexCount().collect { dataState ->
            when (dataState) {
                is DataResource.Error -> {
                    Log.e(TAG, dataState.throwable.message.toString())
                }

                is DataResource.Loading -> {
                    Log.d(TAG, "튜플 개수 가져오는 중 ")
                }

                is DataResource.Success -> {
                    Log.d(TAG, "튜플 개수 가져오기 성공")
                }
            }
        }
        return bindRecentSearchDataSourceImpl.getMaxIndexCount()
    }

    override suspend fun deleteAllData(): Flow<DataResource<Boolean>> {
        bindRecentSearchDataSourceImpl.deleteAllData().collect { dataState ->
            when (dataState) {
                is DataResource.Error -> {
                    Log.e(TAG, dataState.throwable.message.toString())
                }

                is DataResource.Loading -> {
                    Log.d(TAG, "튜플 개수 가져오는 중 ")
                }

                is DataResource.Success -> {
                    Log.d(TAG, "튜플 개수 가져오기 성공")
                }
            }
        }
        return bindRecentSearchDataSourceImpl.deleteAllData()
    }

    companion object {
        const val TAG = "RecenteSearchRepositoryImpl"
    }
}