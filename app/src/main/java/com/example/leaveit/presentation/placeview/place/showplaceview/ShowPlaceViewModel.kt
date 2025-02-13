package com.example.leaveit.presentation.placeview.place.showplaceview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.PlaceDomainModel
import com.example.leaveit.domain.usecase.place.GetPlaceUseCaseInterface
import com.example.leaveit.domain.usecase.place.recentSearchPlace.RecentSearchPlaceUseCase
import com.example.leaveit.domain.usecase.place.searchPlace.StoreLogSearchKeywordUseCase
import com.example.leaveit.local.PlaceSearch.RecentSearchPlaceEntity
import com.example.leaveit.presentation.placeview.place.showplaceview.DTO.CategoryDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowPlaceViewModel @Inject constructor(
    private val placeUseCase: GetPlaceUseCaseInterface,
    private val storeUseCase : StoreLogSearchKeywordUseCase,
    private val recentSearchPlaceUseCase : RecentSearchPlaceUseCase
) : ViewModel() {


    private val _tourAttractionData: MutableLiveData<List<CategoryDto>?> by lazy { MutableLiveData() }
    val tourAttractionData: LiveData<List<CategoryDto>?> = _tourAttractionData

    private val _cultureData: MutableLiveData<List<CategoryDto>?> by lazy { MutableLiveData() }
    val cultureData: LiveData<List<CategoryDto>?> = _cultureData

    private val _festivalData: MutableLiveData<List<CategoryDto>?> by lazy { MutableLiveData() }
    val festivalData: LiveData<List<CategoryDto>?> = _festivalData

    private val _topAppBarText: MutableLiveData<String> by lazy { MutableLiveData() }
    var topAppBarText: LiveData<String> = _topAppBarText

    private val _searchData: MutableLiveData<PagingData<PlaceDomainModel>> by lazy { MutableLiveData() }
    var searchData: LiveData<PagingData<PlaceDomainModel>> = _searchData

    private val _recentSearchData: MutableLiveData<List<RecentSearchPlaceEntity>> by lazy { MutableLiveData() }
    var recentSearchData: LiveData<List<RecentSearchPlaceEntity>> = _recentSearchData


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

    fun getSearchData(query: String) {
        viewModelScope.launch {
            placeUseCase.getSearchPlaceUseCase(query).cachedIn(viewModelScope)
                .collectLatest { data ->
                    _searchData.value = data
                }
        }
    }

    fun storeKeyWord(query: String){
        viewModelScope.launch {
            storeUseCase.excute(query).collect{
                when(it){
                    is DataResource.Error -> {
                        Log.d(TAG,"검색어 저장 실패 : ${it.throwable.message}")
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG,"검색어 저장 로딩중")
                    }
                    is DataResource.Success -> {
                        Log.d(TAG,"검색어 저장 성공 : ${it.data}")
                    }
                }
            }
        }
    }

    fun setTopAppBarTitleText(value: String) {
        _topAppBarText.value = value
    }

    fun getRecentSearchData(){
        viewModelScope.launch {
            recentSearchPlaceUseCase.getData().collect{
                when(it){
                    is DataResource.Error -> {
                        Log.d(TAG,"최근 검색 기록 불러오기 실패 : ${it.throwable.message.toString()}")
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG,"최근 검색 기록 불러오는 중")
                    }
                    is DataResource.Success -> {
                        _recentSearchData.value = it.data
                    }
                }

            }
        }

    }

    fun setRecentSearchData(value : RecentSearchPlaceEntity){
        viewModelScope.launch {
            recentSearchPlaceUseCase.setData(value).collect{
                when(it){
                    is DataResource.Error -> {
                        Log.d(TAG,"최근 검색 기록 저장 실패 : ${it.throwable.message.toString()}")
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG,"최근 검색 기록 저장 중")
                    }
                    is DataResource.Success -> {
                      Log.d(TAG,"최근 검색 기록 저장 성공")
                    }
                }

            }
        }
    }

    fun deleteRecentSearchData(value : String){
        viewModelScope.launch {
            recentSearchPlaceUseCase.getData().collect{
                when(it){
                    is DataResource.Error -> {
                        Log.d(TAG,"$value 최근 검색 기록 삭제 실패 : ${it.throwable.message.toString()}")
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG,"$value 최근 검색 기록 삭제 중")
                    }
                    is DataResource.Success -> {
                        Log.d(TAG,"$value 최근 검색 기록 삭제 성공")
                    }
                }

            }
        }
    }

    fun deleteAllRecentSearchData(){
        viewModelScope.launch {
            recentSearchPlaceUseCase.getData().collect{
                when(it){
                    is DataResource.Error -> {
                        Log.d(TAG,"최근 검색 기록 전체 삭제 실패 : ${it.throwable.message.toString()}")
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG,"최근 검색 기록 전체 삭제 중")
                    }
                    is DataResource.Success -> {
                        Log.d(TAG,"최근 검색 기록 전체 삭제 성공")
                    }
                }

            }
        }
    }

    fun initFestivalCategoryData(): List<CategoryDto> {
        return listOf(
            CategoryDto(
                title = "축제",
                contentTypeId = "축제",
                image = "http://tong.visitkorea.or.kr/cms/resource/94/3332394_image2_1.jpg",
            ),
            CategoryDto(
                title = "공연",
                contentTypeId = "공원",
                image = "https://tong.visitkorea.or.kr/cms/resource/89/3080589_image2_1.JPG",
            ),
            CategoryDto(
                title = "전시회",
                contentTypeId = "전시회",
                image = "http://tong.visitkorea.or.kr/cms/resource/68/3081168_image2_1.JPG",
            ),
            CategoryDto(
                title = "박람회",
                contentTypeId = "박람회",
                image = "https://tong.visitkorea.or.kr/cms/resource/58/2849758_image2_1.jpg",
            ),
            CategoryDto(
                title = "경기장",
                contentTypeId = "경기장",
                image = "https://tong.visitkorea.or.kr/cms/resource/95/3406895_image2_1.jpg",

                ),
        )
    }

    fun initCultureCategoryData(): List<CategoryDto> {
        return listOf(
            CategoryDto(
                title = "박물관",
                contentTypeId = "박물관",
                image = "http://tong.visitkorea.or.kr/cms/resource/91/3427291_image2_1.jpg",

                ),
            CategoryDto(
                title = "기념관",
                contentTypeId = "기념관",
                image = "http://tong.visitkorea.or.kr/cms/resource/64/3336664_image2_1.JPG",

                ),
            CategoryDto(
                title = "전시관",
                contentTypeId = "전시관",
                image = "http://tong.visitkorea.or.kr/cms/resource/63/3374163_image2_1.JPG",

                ),
            CategoryDto(
                title = "미술관",
                contentTypeId = "미술관",
                image = "http://tong.visitkorea.or.kr/cms/resource/17/2892117_image2_1.jpg",

                ),
            CategoryDto(
                title = "공연장",
                contentTypeId = "공연장",
                image = "http://tong.visitkorea.or.kr/cms/resource/88/2945688_image2_1.jpg",

                ),
            CategoryDto(
                title = "도서관",
                contentTypeId = "도서관",
                image = "http://tong.visitkorea.or.kr/cms/resource/64/3408764_image2_1.jpg",

                ),
            CategoryDto(
                title = "서점",
                contentTypeId = "서점",
                image = "http://tong.visitkorea.or.kr/cms/resource/38/2931938_image2_1.jpg",

                ),
            CategoryDto(
                title = "문화전수시설",
                contentTypeId = "문화전수시설",
                image = "http://tong.visitkorea.or.kr/cms/resource/67/3047867_image2_1.jpg",

                ),
            CategoryDto(
                title = "영화관",
                contentTypeId = "영화관",
                image = "http://tong.visitkorea.or.kr/cms/resource/77/3352177_image2_1.jpg",

                ),
        )
    }

    fun initPlaceCategoryData(): List<CategoryDto> {
        return listOf(
            CategoryDto(
                title = "공원",
                contentTypeId = "공원",
                image = "http://tong.visitkorea.or.kr/cms/resource/28/3432328_image3_1.jpg",

                ),
            CategoryDto(
                title = "산",
                contentTypeId = "산",
                image = "http://tong.visitkorea.or.kr/cms/resource/13/2796513_image2_1.jpg",

                ),
            CategoryDto(
                title = "산림",
                contentTypeId = "산림",
                image = "http://tong.visitkorea.or.kr/cms/resource/07/2747107_image2_1.jpg",

                ),
            CategoryDto(
                title = "계곡",
                contentTypeId = "계곡",
                image = "http://tong.visitkorea.or.kr/cms/resource/47/3341747_image2_1.jpg",

                ),
            CategoryDto(
                title = "해안절경",
                contentTypeId = "해안절경",
                image = "http://tong.visitkorea.or.kr/cms/resource/96/2716496_image2_1.jpg",

                ),
            CategoryDto(
                title = "해수욕장",
                contentTypeId = "해수욕장",
                image = "http://tong.visitkorea.or.kr/cms/resource/85/2921985_image2_1.jpg",

                ),
            CategoryDto(
                title = "섬",
                contentTypeId = "섬",
                image = "http://tong.visitkorea.or.kr/cms/resource/87/3330987_image2_1.jpg",

                ),
            CategoryDto(
                title = "항구/포구",
                contentTypeId = "항구/포구",
                image = "http://tong.visitkorea.or.kr/cms/resource/20/2947320_image2_1.jpg",

                ),
            CategoryDto(
                title = "등대",
                contentTypeId = "등대",
                image = "http://tong.visitkorea.or.kr/cms/resource/14/2714114_image2_1.jpg",

                ),
            CategoryDto(
                title = "호수",
                contentTypeId = "호수",
                image = "http://tong.visitkorea.or.kr/cms/resource/39/3031639_image2_1.JPG",

                ),
            CategoryDto(
                title = "강",
                contentTypeId = "강",
                image = "http://tong.visitkorea.or.kr/cms/resource/84/3334184_image2_1.jpg",

                ),
            CategoryDto(
                title = "동굴",
                contentTypeId = "동굴",
                image = "http://tong.visitkorea.or.kr/cms/resource/13/2987913_image2_1.jpeg",

                ),
            CategoryDto(
                title = "고궁",
                contentTypeId = "고궁",
                image = "http://tong.visitkorea.or.kr/cms/resource/33/2678633_image2_1.jpg",

                ),
            CategoryDto(
                title = "민속마을",
                contentTypeId = "민속마을",
                image = "http://tong.visitkorea.or.kr/cms/resource/62/1946562_image2_1.jpg",

                ),
            CategoryDto(
                title = "유적지",
                contentTypeId = "유적지",
                image = "http://tong.visitkorea.or.kr/cms/resource/16/3044516_image2_1.jpg",

                ),
            CategoryDto(
                title = "종교",
                contentTypeId = "종교",
                image = "http://tong.visitkorea.or.kr/cms/resource/51/2788351_image2_1.jpg",

                ),
            CategoryDto(
                title = "관광단지",
                contentTypeId = "관광단지",
                image = "http://tong.visitkorea.or.kr/cms/resource/03/3361903_image2_1.jpg",

                ),
            CategoryDto(
                title = "욕장",
                contentTypeId = "욕장",
                image = "http://tong.visitkorea.or.kr/cms/resource/80/1591380_image2_1.jpg",

                ),
            CategoryDto(
                title = "테마공원",
                contentTypeId = "테마공원",
                image = "http://tong.visitkorea.or.kr/cms/resource/81/3046781_image2_1.jpg",

                )
        )
    }

    companion object{
        const val TAG = "ShowPlaceViewModel"
    }
}