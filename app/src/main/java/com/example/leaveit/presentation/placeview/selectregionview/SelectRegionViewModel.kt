package com.example.leaveit.presentation.placeview.selectregionview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leaveit.domain.usecase.GetPlaceUseCase
import com.example.leaveit.domain.usecase.GetPlaceUseCaseInterface
import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SelectRegionViewModel @Inject constructor(
    private val getFeedUseCase: GetPlaceUseCase
) : ViewModel() {


    //관광지 가져오는 유스케이스 의존성 주입
    @Inject
    lateinit var bindGetPlaceUseCaseImpl: GetPlaceUseCaseInterface

    private val _places = MutableLiveData<List<SelectRegionModel>>()
    val places: LiveData<List<SelectRegionModel>> = _places

    fun getPlaceInfo(
        contentTypedId: String,
        areaCode: String
    )  = runBlocking(Dispatchers.IO){
        launch {
            try{
                val response  = getFeedUseCase.getPlaceUseCase(
                    contentTypedId = contentTypedId,
                    areaCode = areaCode)

                //UI 스레드에 갱신이 되어야 하기 때문에 liveadata에 값을 등록하려면
                // postValue()로 등록하기
                _places.postValue(response.listToEntity(response))
            }catch (e : Exception){
                 Log.e(TAG,e.message.toString())
            }
        }
    }

    companion object {
        val TAG = "SelectRegionViewModel"
    }

}