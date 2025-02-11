package com.example.leaveit.remote.placeview

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.leaveit.data.model.PlaceDataModel
import com.example.leaveit.remote.api.place.PlaceApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class SearchPlaceViewDataSourceImpl @AssistedInject constructor(
    @Assisted private val service: PlaceApi,
    @Assisted private val query: String
) : PagingSource<Int, PlaceDataModel>() {

    //관광지 정보 호출 데이터소스 인터페이스 구현체
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlaceDataModel> {
        return try {
            // 페이징 숫자 설정
            // 페이징 숫자가 null이라면 1로 설정
            val pageNumber = params.key ?: 1
            Log.d(TAG, query)

            // 서버에서 응답 받아오기
            val response = service.getSearchPlaceData(query = query, num = pageNumber)
            if (response.isSuccessful) {
                Log.d(TAG, "호출성공")
                val body = response.body()
                val places = body?.map { it.toData() } ?: emptyList()

                // 다음 페이지 번호 설정
                // 요청한 데이터가 비어있다면 더이상 데이터가 없는 것이므로 null로 설정
                // 데이터가 있다면 다음 페이지 데이터가 있는것이므로 현재 페이징 숫자 +1로 다음 페이지 번호 설정
                val nextPageNumber = if (places.isEmpty()) null else pageNumber + 1
                // 서버에서 가져온 정보 반환
                LoadResult.Page(
                    data = places, // 서버에서 가져온 데이터 리스트
                    prevKey = if (pageNumber == 1) null else pageNumber - 1, // 첫 페이지면 이전 페이지 없음
                    nextKey = nextPageNumber // 다음 페이지 키 설정
                )
            } else {
                Log.d(TAG, response.code().toString())
                LoadResult.Error(Exception("서버 오류: ${response.code()}"))
            }

        } catch (e: Exception) {
            Log.d(TAG, e.message.toString())
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, PlaceDataModel>): Int? {
        // 현재위치(anchorPosition)을 기반으로 새로고침 할 때 가장 가까운 위치를 prevKey or
        // nextKey로 계산
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }


    companion object {
        val TAG = "SearchPlaceViewDataSourceImpl"
    }
}

@AssistedFactory
interface SearchPlaceViewDataSourceAsstiedFactory {
    fun create(query: String, service: PlaceApi): SearchPlaceViewDataSourceImpl
}