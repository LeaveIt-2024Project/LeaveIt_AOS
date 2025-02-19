package com.example.leaveit.presentation.myprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProfileChangeViewModel : ViewModel() {

    private val _regionList = MutableLiveData<List<MyProfileChangeModel>>()
    val regionList: LiveData<List<MyProfileChangeModel>> get() = _regionList // 선호지역 리스트

    fun loadSpinnerData() {
        _regionList.value = listOf(
            MyProfileChangeModel("1", "서울", "서울특별시"),
            MyProfileChangeModel("2", "경기", "경기도"),
            MyProfileChangeModel("3", "인천", "인천광역시"),
            MyProfileChangeModel("4", "부산", "부산광역시")
        )
    }
}