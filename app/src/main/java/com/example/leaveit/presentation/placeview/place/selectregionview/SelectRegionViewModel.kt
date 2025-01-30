package com.example.leaveit.presentation.placeview.place.selectregionview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.domain.usecase.place.GetPlaceUseCase
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectRegionViewModel @Inject constructor(
    private val getFeedUseCase: GetPlaceUseCase
) : ViewModel() {

    private val _placeContentId : MutableLiveData<String> by lazy { MutableLiveData() }
    val placeContentId : LiveData<String> = _placeContentId

    private val _isMoveDetailView : MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val isMoveDetailView : LiveData<Boolean> = _isMoveDetailView

    private val _contentTypeId : MutableLiveData<String> by lazy { MutableLiveData() }
    val contentTypeId : LiveData<String> = _contentTypeId

    private val _topTapContent : MutableLiveData<String> by lazy { MutableLiveData() }
    val topTapContent : LiveData<String> = _topTapContent

    private val _mapx : MutableLiveData<String> by lazy { MutableLiveData() }
    val mapx : LiveData<String> = _mapx

    private val _mapy : MutableLiveData<String> by lazy { MutableLiveData() }
    val mapy : LiveData<String> = _mapy

    private val _placeLocation : MutableLiveData<LatLng> by lazy { MutableLiveData() }
    val placeLocation : LiveData<LatLng> = _placeLocation

    private val _imageUrl : MutableLiveData<String> by lazy { MutableLiveData() }
    val imageUrl : LiveData<String> = _imageUrl

    private val _addressInfo : MutableLiveData<String> by lazy { MutableLiveData() }
    val addressInfo : LiveData<String> = _addressInfo

    private val _placeTitle : MutableLiveData<String> by lazy { MutableLiveData() }
    val placeTitle : LiveData<String> = _placeTitle


    init {
        _isMoveDetailView.value = true
    }


    fun setContentId(value : String){
        _placeContentId.value = value
    }

    fun setIsMoveDetailView(value : Boolean){
        _isMoveDetailView.value = value
    }

    fun setTopTapContent(value : String){
        _topTapContent.value = value
    }

    fun setMapX(value : String){
        _mapx.value = value
    }

    fun setMapY(value : String){
        _mapy.value = value
    }

    fun setPlaceImage(value : String){
        _imageUrl.value = value
    }

    fun setAddressInfo(value : String){
        _addressInfo.value = value
    }

    fun setPlaceLocation(lat : String, long : String){
        _placeLocation.value = LatLng(lat.toDouble(),long.toDouble())
    }

    fun setPlaceTitle(value : String){
        _placeTitle.value = value
    }

    fun setContentTypeId(value : String){
        _contentTypeId.value = value
    }

    companion object {
        val TAG = "SelectRegionViewModel"
    }

}