package com.example.leaveit.data.placeview

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.leaveit.domain.model.PlaceDomainModel
import com.example.leaveit.domain.usecase.place.GetPlaceRepositoryInterface
import com.example.leaveit.remote.api.place.PlaceApi
import com.example.leaveit.remote.placeview.PlaceViewDataSourceAsstiedFactory
import com.example.leaveit.remote.placeview.SearchPlaceViewDataSourceAsstiedFactory
import com.example.leaveit.remote.placeview.SortByRegionPlaceViewDataSourceAsstiedFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class PlaceViewRepositoryImpl @Inject constructor(
    private val service: PlaceApi,
    private val SortByRegionPlaceViewDataSourceAsstiedFactory: SortByRegionPlaceViewDataSourceAsstiedFactory,
    private val PlaceViewDataSourceAsstiedFactory : PlaceViewDataSourceAsstiedFactory,
    private val SearchPlaceViewDataSourceAsstiedFactory : SearchPlaceViewDataSourceAsstiedFactory
) : GetPlaceRepositoryInterface {
    // 관광지 레포지 인터페이스 구현 뷰
    override suspend fun getAllPlaceData(category: String): Flow<PagingData<PlaceDomainModel>> {
        Log.d(TAG, "호출")
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PlaceViewDataSourceAsstiedFactory.create(
                    category = category,
                    service = service
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
            .catch { e ->
                emit(PagingData.empty())
                Log.e(TAG, "전체 데이터 로드 실패: ${e.message}", e)
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getSortByRegionPlaceData(
        areadCode: String,
        category: String
    ): Flow<PagingData<PlaceDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SortByRegionPlaceViewDataSourceAsstiedFactory.create(
                    category = category,
                    areaCode = areadCode,
                    service = service
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
            .catch { e ->
                emit(PagingData.empty())
                Log.e(TAG, "지역별 데이터 로드 실패: ${e.message}", e)
            }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun getSearchPlaceData(query: String): Flow<PagingData<PlaceDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                SearchPlaceViewDataSourceAsstiedFactory.create(
                    query = query,
                    service = service
                )
            }
        ).flow
            .map { pagingData ->
                pagingData.map { it.toDomain() }
            }
            .catch { e ->
                emit(PagingData.empty())
                Log.e(TAG, "검색 데이터 로드 실패: ${e.message}", e)
            }
            .flowOn(Dispatchers.IO)
    }

    companion object {
        const val TAG = "PlaceViewRespoitoryImpl"
    }
}