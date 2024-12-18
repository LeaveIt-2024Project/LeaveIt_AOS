package com.example.leaveit.presentation.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.domain.usecase.review.ReviewUseCaseInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
) : ViewModel() {

    private val _data: MutableLiveData<List<ReviewDataModel>> by lazy { MutableLiveData() }
    val data: LiveData<List<ReviewDataModel>> = _data

    @Inject
    lateinit var getReview: ReviewUseCaseInterface

    // PagingData Flow 생성 (query에 맞는 페이징 데이터를 가져옴)
    suspend fun getReviewSortByRegion(query: Int): Flow<PagingData<ReviewDataModel>> {
        // cachedIn로 페이징된 데이터를 메모리에 캐시해서 중복된 로드를 방지
        return getReview.getSortByRegion(query).cachedIn(viewModelScope)
    }

    suspend fun getReviewSortByLike(query: Int) :  Flow<PagingData<ReviewDataModel>> {
        return getReview.getSortByLike(query).cachedIn(viewModelScope)
    }

    suspend fun getReviewSortByRank(query: Int) :  Flow<PagingData<ReviewDataModel>> {
        return getReview.getSortByRank(query).cachedIn(viewModelScope)
    }


    fun convertStrRegionCodeToInt(str: String): Int {
        var result = 0
        when (str) {
            "서울" -> result = 1
            "인천" -> result = 2
            "대전" -> result = 3
            "대구" -> result = 4
            "광주" -> result = 5
            "부산" -> result = 6
            "울산" -> result = 7
            "세종" -> result = 8
            "경기" -> result = 31
            "강원" -> result = 32
            "충북" -> result = 33
            "충남" -> result = 34
            "경북" -> result = 35
            "경남" -> result = 36
            "전북" -> result = 37
            "전남" -> result = 38
            "제주" -> result = 39
        }

        return result
    }

    companion object {
        val TAG = "ReviewViewModel"
    }


}