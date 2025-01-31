package com.example.leaveit.presentation.placeview.place.showplaceview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.presentation.placeview.place.showplaceview.DTO.CategoryDto

class ShowPlaceViewModel : ViewModel() {

    private val _tourAttractionData: MutableLiveData<List<CategoryDto>?> by lazy { MutableLiveData() }
    val tourAttractionData: LiveData<List<CategoryDto>?> = _tourAttractionData

    private val _cultureData: MutableLiveData<List<CategoryDto>?> by lazy { MutableLiveData() }
    val cultureData: LiveData<List<CategoryDto>?> = _cultureData

    private val _festivalData: MutableLiveData<List<CategoryDto>?> by lazy { MutableLiveData() }
    val festivalData: LiveData<List<CategoryDto>?> = _festivalData

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

    fun initFestivalCategoryData(): List<CategoryDto> {
        return listOf(
            CategoryDto(
                title = "축제",
                contentTypeId = "15",
                image = "http://tong.visitkorea.or.kr/cms/resource/94/3332394_image2_1.jpg",
            ),
            CategoryDto(
                title = "공연",
                contentTypeId = "15",
                image = "https://tong.visitkorea.or.kr/cms/resource/89/3080589_image2_1.JPG",
            ),
            CategoryDto(
                title = "전시회",
                contentTypeId = "15",
                image = "http://tong.visitkorea.or.kr/cms/resource/68/3081168_image2_1.JPG",
            ),
            CategoryDto(
                title = "박람회",
                contentTypeId = "15",
                image = "https://tong.visitkorea.or.kr/cms/resource/58/2849758_image2_1.jpg",
            ),
            CategoryDto(
                title = "경기장",
                contentTypeId = "15",
                image = "https://tong.visitkorea.or.kr/cms/resource/95/3406895_image2_1.jpg",
            ),
        )
    }

    fun initCultureCategoryData(): List<CategoryDto> {
        return listOf(
            CategoryDto(
                title = "박물관",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/91/3427291_image2_1.jpg",

                ),
            CategoryDto(
                title = "기념관",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/64/3336664_image2_1.JPG",

                ),
            CategoryDto(
                title = "전시관",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/63/3374163_image2_1.JPG",
                ),
            CategoryDto(
                title = "미술관",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/17/2892117_image2_1.jpg",
                ),
            CategoryDto(
                title = "공연장",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/88/2945688_image2_1.jpg",
                ),
            CategoryDto(
                title = "도서관",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/64/3408764_image2_1.jpg",
                ),
            CategoryDto(
                title = "서점",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/38/2931938_image2_1.jpg",
                ),
            CategoryDto(
                title = "문화전수시설",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/67/3047867_image2_1.jpg",
                ),
            CategoryDto(
                title = "영화관",
                contentTypeId = "14",
                image = "http://tong.visitkorea.or.kr/cms/resource/77/3352177_image2_1.jpg",
                ),
        )
    }

    fun initPlaceCategoryData(): List<CategoryDto> {
        return listOf(
            CategoryDto(
                title = "공원",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/28/3432328_image3_1.jpg",
                ),
            CategoryDto(
                title = "산",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/13/2796513_image2_1.jpg",
                ),
            CategoryDto(
                title = "산림",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/07/2747107_image2_1.jpg",
                ),
            CategoryDto(
                title = "계곡",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/47/3341747_image2_1.jpg",
                ),
            CategoryDto(
                title = "해안절경",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/96/2716496_image2_1.jpg",
                ),
            CategoryDto(
                title = "해수욕장",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/85/2921985_image2_1.jpg",
                ),
            CategoryDto(
                title = "섬",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/87/3330987_image2_1.jpg",
                ),
            CategoryDto(
                title = "항구/포구",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/20/2947320_image2_1.jpg",
                ),
            CategoryDto(
                title = "등대",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/14/2714114_image2_1.jpg",
                ),
            CategoryDto(
                title = "호수",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/39/3031639_image2_1.JPG",
                ),
            CategoryDto(
                title = "강",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/84/3334184_image2_1.jpg",
                ),
            CategoryDto(
                title = "동굴",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/13/2987913_image2_1.jpeg",
                ),
            CategoryDto(
                title = "고궁",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg",
                ),
            CategoryDto(
                title = "민속마을",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/62/1946562_image2_1.jpg",
                ),
            CategoryDto(
                title = "유적지",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/16/3044516_image2_1.jpg",
                ),
            CategoryDto(
                title = "종교",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/51/2788351_image2_1.jpg",
                ),
            CategoryDto(
                title = "관광단지",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/03/3361903_image2_1.jpg",
                ),
            CategoryDto(
                title = "욕장",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/80/1591380_image2_1.jpg",
                ),
            CategoryDto(
                title = "테마공원",
                contentTypeId = "12",
                image = "http://tong.visitkorea.or.kr/cms/resource/81/3046781_image2_1.jpg",
            )
        )
    }
}