package com.example.leaveit.presentation.mainpageview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.example.leaveit.remote.api.NaverRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPreferRegionViewModel : ViewModel() {

    // LiveData로 장소 리스트 관리
    private val _mainPreferRegionData = MutableLiveData<List<MainPreferRegionModel>>()
    val mainPreferRegionData: LiveData<List<MainPreferRegionModel>> get() = _mainPreferRegionData

    // 데이터 로딩
    fun loadPreferRegions() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = NaverRetrofit.searchApi.getPreferRegions(
                    query = "서울 여행",
                    display = 5,
                    start = 1,
                    sort = "comment")
                val regions = response.items.mapIndexed { index, item ->
                    MainPreferRegionModel(
                        contentId = index + 1,
                        num = (index + 1).toString(),
                        name = item.title,
                        image = item.link)
                }
                _mainPreferRegionData.postValue(regions)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        // 예시로 로컬 데이터나 API 호출을 통해 places 데이터를 설정
        _mainPreferRegionData.value = listOf(
            MainPreferRegionModel(contentId = 11, num = "1", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/71/2777971_image2_1.jpg"),
            MainPreferRegionModel(contentId = 12, num = "6", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/96/2738396_image2_1.jpg"),
            MainPreferRegionModel(contentId = 13, num = "2", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/36/2022436_image2_1.jpg"),
            MainPreferRegionModel(contentId = 14, num = "7", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/99/2543799_image2_1.JPG"),
            MainPreferRegionModel(contentId = 15, num = "3", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/81/3070081_image2_1.jpg"),
            MainPreferRegionModel(contentId = 16, num = "8", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/44/700644_image2_1.jpg"),
            MainPreferRegionModel(contentId = 17, num = "4", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/61/2716261_image2_1.jpg"),
            MainPreferRegionModel(contentId = 18, num = "9", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/81/3342781_image2_1.jpg"),
            MainPreferRegionModel(contentId = 19, num = "5", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/33/2799833_image2_1.JPG"),
            MainPreferRegionModel(contentId = 20, num = "10", name="관광지명", image = "http://tong.visitkorea.or.kr/cms/resource/02/2866402_image2_1.JPG"),
        )
    }
}
