package com.example.leaveit.presentation.mainpageview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.presentation.mainpageview.MainPageViewPagerItem

class MainPageViewModel : ViewModel() {

    // LiveData로 장소 리스트 관리
    private val _mainPageAttractionData = MutableLiveData<List<MainPageViewPagerItem>>()
    val mainPageAttractionData: LiveData<List<MainPageViewPagerItem>> get() = _mainPageAttractionData

    // 데이터 로딩
    fun loadPlaces() {
        // 예시로 로컬 데이터나 API 호출을 통해 places 데이터를 설정
        _mainPageAttractionData.value = listOf(
            MainPageViewPagerItem(contentId = "1",title = "서울", image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            MainPageViewPagerItem(contentId = "1",title = "부산", image = "http://tong.visitkorea.or.kr/cms/resource/00/2626200_image2_1.jpg"),
            MainPageViewPagerItem(contentId = "1",title = "제주", image = "http://tong.visitkorea.or.kr/cms/resource/36/3079736_image2_1.jpg")
        )
    }
}
