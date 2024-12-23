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


    fun setRegion(str : String){
        _selectRegion.value  = str
    }

    fun setTopAppBarTitleText(str : String){
        _topAppBarText.value = str
    }







}