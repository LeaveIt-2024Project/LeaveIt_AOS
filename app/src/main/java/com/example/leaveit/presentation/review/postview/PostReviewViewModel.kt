package com.example.leaveit.presentation.review.postview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostReviewViewModel @Inject constructor() : ViewModel() {

    private val _selectRegion : MutableLiveData<String> by lazy { MutableLiveData() }
    var selectRegion : LiveData<String> = _selectRegion

    private val _topAppBarText : MutableLiveData<String> by lazy { MutableLiveData() }
    var topAppBarText : LiveData<String> = _topAppBarText

    private val _starCount : MutableLiveData<Int> by lazy { MutableLiveData() }
    var starCount : LiveData<Int> = _starCount

    fun setRegion(value : String){
        _selectRegion.value  = value
    }

    fun setStarCount(value : Int){
        _starCount.value = value
    }

    fun setTopAppBarTitleText(value : String){
        _topAppBarText.value = value
    }
}