package com.example.leaveit.presentation.placeview.place.selectregionview

import androidx.lifecycle.ViewModel
import com.example.leaveit.domain.usecase.place.GetPlaceUseCase
import com.example.leaveit.domain.usecase.place.GetPlaceUseCaseInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectRegionViewModel @Inject constructor(
    private val getFeedUseCase: GetPlaceUseCase
) : ViewModel() {


    //관광지 가져오는 유스케이스 의존성 주입
    @Inject
    lateinit var bindGetPlaceUseCaseImpl: GetPlaceUseCaseInterface


    companion object {
        val TAG = "SelectRegionViewModel"
    }

}