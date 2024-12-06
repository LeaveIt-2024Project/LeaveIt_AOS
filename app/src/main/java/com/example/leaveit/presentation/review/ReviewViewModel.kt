package com.example.leaveit.presentation.review

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.data.model.ReviewDataModel
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.usecase.review.ReviewUsecaseInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor() : ViewModel() {

    private val _data : MutableLiveData<List<ReviewDataModel>> by lazy { MutableLiveData() }
    val data : LiveData<List<ReviewDataModel>> = _data

    @Inject
    lateinit var test : ReviewUsecaseInterface

    init {
        viewModelScope.launch {
            test.getReviewDataTest().collect{
                when(it){
                    is DataResource.Loading -> Log.d(TAG,"로딩중")
                    is DataResource.Success -> {
                        Log.d(TAG,"데이터 부르기 성공")
                        _data.value = it.data
                    }
                    is DataResource.Error -> Log.d(TAG,it.throwable.message.toString())
                }
            }
        }
    }



    companion object{
        val TAG = "ReviewViewModel"
    }


}