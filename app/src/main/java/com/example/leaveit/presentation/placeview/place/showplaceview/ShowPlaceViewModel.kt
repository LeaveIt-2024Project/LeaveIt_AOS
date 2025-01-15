package com.example.leaveit.presentation.placeview.place.showplaceview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel

class ShowPlaceViewModel : ViewModel() {

    private val _tourAttractionData : MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val tourAttractionData : LiveData<List<SelectRegionModel>?> = _tourAttractionData

    private val _cultureData : MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val cultureData : LiveData<List<SelectRegionModel>?> = _cultureData

    private val _festivalData : MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val festivalData : LiveData<List<SelectRegionModel>?> = _festivalData

    private val _topAppBarText: MutableLiveData<String> by lazy { MutableLiveData() }
    var topAppBarText: LiveData<String> = _topAppBarText

    fun getFeedData(){
        // TODO 피드 서버 API 구현되면 Domain 로직에서 GetFeedUseCase를 통해 데이터 가져오기
        //        _currentData.value = GetFeedUseCase
    }

    fun testGetValue(){

        val initdata = listOf(
            SelectRegionModel(contentId = "2733967" ,
                title =  "가회동 성당",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/09/3303909_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            )
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

    fun setTopAppBarTitleText(value: String) {
        _topAppBarText.value = value
    }

}