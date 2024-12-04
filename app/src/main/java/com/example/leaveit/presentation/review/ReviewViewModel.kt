package com.example.leaveit.presentation.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.data.model.ReviewDataModel
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
                _data.value = it
            }
        }
    }




}