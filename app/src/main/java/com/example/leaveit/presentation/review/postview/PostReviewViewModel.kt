package com.example.leaveit.presentation.review.postview

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.data.recent_search.RecentSearchRepositoryImpl
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.usecase.recent_data.RecentSearchUseCase
import com.example.leaveit.local.RecentSearchEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class PostReviewViewModel @Inject constructor(
    private val recentSearchUseCaseImpl: RecentSearchUseCase
) : ViewModel() {

    private val _selectRegion: MutableLiveData<String> by lazy { MutableLiveData() }
    var selectRegion: LiveData<String> = _selectRegion

    private val _topAppBarText: MutableLiveData<String> by lazy { MutableLiveData() }
    var topAppBarText: LiveData<String> = _topAppBarText

    private val _starCount: MutableLiveData<Int> by lazy { MutableLiveData() }
    var starCount: LiveData<Int> = _starCount

    private val _imageList: MutableLiveData<List<File>> by lazy { MutableLiveData() }
    var imageList: LiveData<List<File>> = _imageList

    private val _tempImageList: MutableLiveData<List<Uri>> by lazy { MutableLiveData() }
    var tempImageList: LiveData<List<Uri>> = _tempImageList

    private val _recentSearchList: MutableLiveData<MutableList<RecentSearchEntity>> by lazy { MutableLiveData() }
    var recentSearchList: LiveData<MutableList<RecentSearchEntity>> = _recentSearchList

    fun setRegion(value: String) {
        _selectRegion.value = value
    }

    fun setStarCount(value: Int) {
        if (_starCount.value != null) {
            _starCount.value = null
        }
        _starCount.value = value
    }

    fun setTopAppBarTitleText(value: String) {
        _topAppBarText.value = value
    }

    fun setImageList(value: List<File>) {
        _imageList.value = value
    }

    fun setTempImageList(value: List<Uri>) {
        if (!_tempImageList.value.isNullOrEmpty()) {
            _tempImageList.value = null // 기존 데이터 초기화
        }

        // 새로운 값 설정
        _tempImageList.value = value
    }

    fun getAllData() {
        CoroutineScope(Dispatchers.IO).launch {
            recentSearchUseCaseImpl.getAllData().collectLatest { dataState ->
                when (dataState) {
                    is DataResource.Error -> {
                        Log.e(
                            RecentSearchRepositoryImpl.TAG,
                            dataState.throwable.message.toString()
                        )
                    }

                    is DataResource.Loading -> {
                        Log.d(RecentSearchRepositoryImpl.TAG, "데이터 불러오는 중")
                    }

                    is DataResource.Success -> {
                        val list = dataState.data

                        _recentSearchList.postValue(list.toMutableList())
                        Log.d(RecentSearchRepositoryImpl.TAG, "데이터 불러오기 성공")
                    }
                }
            }
        }
    }

    fun setRecentQuery(data: RecentSearchEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            recentSearchUseCaseImpl.addTuple(data).collect { dataState ->
                when (dataState) {
                    is DataResource.Error -> {
                        Log.e(
                            TAG,
                            dataState.throwable.message.toString()
                        )
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG, "데이터 저장 중")
                    }

                    is DataResource.Success -> {
                        Log.d(TAG, "데이터 저장 성공 : ${dataState.data}")
                    }
                }
            }
        }
    }

    fun deleteRecentQuery(){
        CoroutineScope(Dispatchers.IO).launch {
            recentSearchUseCaseImpl.deleteTuple().collect{ dataState ->
                when (dataState) {
                    is DataResource.Error -> {
                        Log.e(
                            TAG,
                            dataState.throwable.message.toString()
                        )
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG, "데이터 삭제 중")
                    }

                    is DataResource.Success -> {
                        Log.d(TAG, "데이터 삭제 성공 : ${dataState.data}")
                        val list = dataState.data

                        _recentSearchList.postValue(list.toMutableList())
                    }
                }
            }
        }
    }

    fun deleteAllQuery(){
        CoroutineScope(Dispatchers.IO).launch {
            recentSearchUseCaseImpl.deleteAllData().collect{ dataState ->
                when (dataState) {
                    is DataResource.Error -> {
                        Log.e(
                            TAG,
                            dataState.throwable.message.toString()
                        )
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG, "데이터 전체 삭제 중")
                    }

                    is DataResource.Success -> {
                        Log.d(TAG, "데이터 전체 삭제 성공 : ${dataState.data}")
                        _recentSearchList.postValue(mutableListOf())
                    }
                }
            }
        }
    }


    companion object {
        const val TAG = "PostReviewViewModel"
    }
}