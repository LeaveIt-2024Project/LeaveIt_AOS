package com.example.leaveit.presentation.placeview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.domain.usecase.GetFeedUseCase

class ShowPlaceViewModel : ViewModel() {

    private val _currentData : MutableLiveData<List<ShowPlaceModel>?> by lazy { MutableLiveData() }
    val currentData : LiveData<List<ShowPlaceModel>?> = _currentData

    fun getFeedData(){
        // TODO 피드 서버 API 구현되면 Domain 로직에서 GetFeedUseCase를 통해 데이터 가져오기
        //        _currentData.value = GetFeedUseCase
    }

    fun testGetValue(){

        val initdata = listOf(
            ShowPlaceModel(contentId = 1 , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            ShowPlaceModel(contentId = 1 , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            ShowPlaceModel(contentId = 1 , title =  "서울 타워","http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg")
        )

        _currentData.apply {
            value = initdata
        }
    }

}