package com.example.leaveit.presentation.placeview.place.selectregionview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.domain.usecase.place.GetPlaceUseCase
import com.example.leaveit.domain.usecase.place.GetPlaceUseCaseInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectRegionViewModel @Inject constructor(
    private val getFeedUseCase: GetPlaceUseCase
) : ViewModel() {


    private val _placeContentId : MutableLiveData<String> by lazy { MutableLiveData() }
    val placeContentId : LiveData<String> = _placeContentId

    //관광지 가져오는 유스케이스 의존성 주입
    @Inject
    lateinit var bindGetPlaceUseCaseImpl: GetPlaceUseCaseInterface


    fun setContentId(value : String){
        _placeContentId.value = value
    }



    companion object {
        val TAG = "SelectRegionViewModel"
    }

}