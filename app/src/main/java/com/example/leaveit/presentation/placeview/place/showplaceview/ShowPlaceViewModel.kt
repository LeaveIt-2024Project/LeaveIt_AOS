package com.example.leaveit.presentation.placeview.place.showplaceview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModel

class ShowPlaceViewModel : ViewModel() {

    private val _tourAttractionData: MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val tourAttractionData: LiveData<List<SelectRegionModel>?> = _tourAttractionData

    private val _cultureData: MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val cultureData: LiveData<List<SelectRegionModel>?> = _cultureData

    private val _festivalData: MutableLiveData<List<SelectRegionModel>?> by lazy { MutableLiveData() }
    val festivalData: LiveData<List<SelectRegionModel>?> = _festivalData

    private val _topAppBarText: MutableLiveData<String> by lazy { MutableLiveData() }
    var topAppBarText: LiveData<String> = _topAppBarText

    fun categoryData() {
        _tourAttractionData.apply {
            value = initPlaceCategoryData()
        }

        _cultureData.apply {
            value = initCultureCategoryData()
        }

        _festivalData.apply {
            value = initFestivalCategoryData()
        }
    }

    fun setTopAppBarTitleText(value: String) {
        _topAppBarText.value = value
    }

    fun initFestivalCategoryData(): List<SelectRegionModel> {
        return listOf(
            SelectRegionModel(
                contentId = "2733967",
                title = "축제",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/94/3332394_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "극장",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/09/3303909_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "전시회",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/68/3081168_image2_1.JPG",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "박람회",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/09/3303909_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "경기장",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/09/3303909_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
        )
    }

    fun initCultureCategoryData(): List<SelectRegionModel> {
        return listOf(
            SelectRegionModel(
                contentId = "2733967",
                title = "박물관",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/91/3427291_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "기념관",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/64/3336664_image2_1.JPG",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "전시관",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/63/3374163_image2_1.JPG",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "미술관",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/17/2892117_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "공연장",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/88/2945688_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "도서관",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/64/3408764_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "서점",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/38/2931938_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "문화전수시설",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/67/3047867_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "영화관",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/77/3352177_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
        )
    }

    fun initPlaceCategoryData(): List<SelectRegionModel> {
        return listOf(
            SelectRegionModel(
                contentId = "2733967",
                title = "공원",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/28/3432328_image3_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "산",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/13/2796513_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "산림",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/07/2747107_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ), SelectRegionModel(
                contentId = "2733967",
                title = "계곡",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/47/3341747_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "해안절경",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/96/2716496_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "해수욕장",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/85/2921985_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "섬",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/87/3330987_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "항구/포구",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/20/2947320_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "등대",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/14/2714114_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "호수",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/39/3031639_image2_1.JPG",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "강",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/84/3334184_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "동굴",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/13/2987913_image2_1.jpeg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "고궁",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "민속마을",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/62/1946562_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "유적지",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/16/3044516_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "종교",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/51/2788351_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "관광단지",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/03/3361903_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "욕장",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/80/1591380_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            ),
            SelectRegionModel(
                contentId = "2733967",
                title = "테마공원",
                contentTypeId = "1",
                areaCode = 2,
                image = "http://tong.visitkorea.or.kr/cms/resource/81/3046781_image2_1.jpg",
                mapx = "126.9846616856",
                mapy = "37.5820858828",
                address = "서울특별시 종로구 북촌로 57 (가회동)"
            )
        )
    }
}