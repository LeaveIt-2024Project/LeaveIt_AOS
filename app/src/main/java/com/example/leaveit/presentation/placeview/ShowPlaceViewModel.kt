package com.example.leaveit.presentation.placeview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.domain.usecase.GetFeedUseCase

class ShowPlaceViewModel : ViewModel() {

    private val _tourAttractionData : MutableLiveData<List<ShowPlaceModel>?> by lazy { MutableLiveData() }
    val tourAttractionData : LiveData<List<ShowPlaceModel>?> = _tourAttractionData

    private val _cultureData : MutableLiveData<List<ShowPlaceModel>?> by lazy { MutableLiveData() }
    val cultureData : LiveData<List<ShowPlaceModel>?> = _cultureData

    private val _festivalData : MutableLiveData<List<ShowPlaceModel>?> by lazy { MutableLiveData() }
    val festivalData : LiveData<List<ShowPlaceModel>?> = _festivalData

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

        _tourAttractionData.apply {
            value = initdata
        }

        _cultureData.apply {
            value = initdata
        }

        _festivalData.apply {
            value = initdata
        }




    }

}