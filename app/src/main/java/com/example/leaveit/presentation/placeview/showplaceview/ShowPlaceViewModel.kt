package com.example.leaveit.presentation.placeview.showplaceview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModel

class ShowPlaceViewModel : ViewModel() {

    private val _tourAttractionData : MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val tourAttractionData : LiveData<List<SelectRegionModel>?> = _tourAttractionData

    private val _cultureData : MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val cultureData : LiveData<List<SelectRegionModel>?> = _cultureData

    private val _festivalData : MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val festivalData : LiveData<List<SelectRegionModel>?> = _festivalData

    fun getFeedData(){
        // TODO 피드 서버 API 구현되면 Domain 로직에서 GetFeedUseCase를 통해 데이터 가져오기
        //        _currentData.value = GetFeedUseCase
    }

    fun testGetValue(){

        val initdata = listOf(
            SelectRegionModel(contentId = "1" , title =  "서울 타워", contentTypeId = "1",areaCode = 2,image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            SelectRegionModel(contentId = "1" , title =  "서울 타워", contentTypeId = "1",areaCode = 2,image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
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