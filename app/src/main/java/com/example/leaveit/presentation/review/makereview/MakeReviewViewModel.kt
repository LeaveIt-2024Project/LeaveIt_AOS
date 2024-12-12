package com.example.leaveit.presentation.review.makereview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeReviewViewModel @Inject constructor() : ViewModel() {

    private val _selectRegion : MutableLiveData<String> by lazy { MutableLiveData() }
    var selectRegion : LiveData<String> = _selectRegion

    fun setRegion(str : String){
        _selectRegion.value  = str
    }

}