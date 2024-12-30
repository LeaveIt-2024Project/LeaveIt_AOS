package com.example.leaveit.remote.review

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.remote.api.review.ReviewApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReviewSortByLikePagingSource @Inject constructor(
    private val apiService : ReviewApi,
    private val query : Int
)  : PagingSource<Int, ReviewDataModel>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewDataModel> {
        val page = params.key ?: 0 // 첫 페이지는 0으로 설정
        return try {
            // API 호출 - page와 pageSize를 기반으로 데이터를 요청
            val response = apiService.getLikedReview(query, page = page)

            // ReviewDataModel 가져오기
            val reviewList = response.entityToData()


            // LoadResult 반환 (prevKey: 이전 페이지, nextKey: 다음 페이지)
            LoadResult.Page(
                data = reviewList,
                prevKey = if (page == 0) null else page - 1, // 첫 페이지라면 prevKey는 null
                nextKey = if (response.content.lastIndex == -1) null else page + 1 // 마지막 페이지라면 nextKey는 null
            )
        } catch (exception: IOException) {
            // 네트워크 오류 처리
            Log.d(TAG,"네트워크 오류${exception.localizedMessage}")

            Log.d(TAG,"네트워크 오류${exception.stackTrace}")
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            // HTTP 오류 처리\
            Log.d(TAG,"HTTP 오류${exception.message}")
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ReviewDataModel>): Int? {
        // 페이징 목록이 새로고침될 때 호출됨 (중간 위치의 키를 반환)
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    companion object{
        const val TAG = "ReviewSortByRegionPagingSource"
    }
}
