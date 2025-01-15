package com.example.leaveit.presentation.placeview.place.navigateplaceview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.PathDomainModel
import com.example.leaveit.domain.usecase.path.GetPathUseCaseInterface
import com.example.leaveit.domain.usecase.reverse_geocoding.ReverseGeoCodingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigatePlaceViewModel @Inject constructor(
    private val getCurrentLocationAddress: ReverseGeoCodingUseCase,
    private val getPathUseCase : GetPathUseCaseInterface
) : ViewModel() {

    private val _currentAddress: MutableLiveData<String> by lazy { MutableLiveData() }
    val currentAddress: LiveData<String> = _currentAddress

    private val _currentLongitude: MutableLiveData<String> by lazy { MutableLiveData() }
    val currentLongitude: LiveData<String> = _currentLongitude

    private val _currentLatitude: MutableLiveData<String> by lazy { MutableLiveData() }
    val currentLatitude : LiveData<String> = _currentLatitude

    private val _pathData: MutableLiveData<PathDomainModel> by lazy { MutableLiveData() }
    val pathData : LiveData<PathDomainModel> = _pathData

    fun getCurrentLocationAddress(value: String) {
        viewModelScope.launch {
            getCurrentLocationAddress.getReverseGeoCodingData(value).collect { state ->
                when (state) {
                    is DataResource.Error -> {
                        Log.e(TAG, state.throwable.toString())
                    }

                    is DataResource.Loading -> {
                        Log.d(TAG, "현 위치 받아오기 로딩중")
                    }

                    is DataResource.Success -> {
                        _currentAddress.value = state.data.address
                    }
                }
            }
        }
    }

    fun getPath(start : String, goal : String){
        viewModelScope.launch {
            getPathUseCase.getPath(start,goal).collect{state ->
                when(state){
                    is DataResource.Error -> {
                        Log.e(TAG, state.throwable.toString())
                    }
                    is DataResource.Loading -> {
                        Log.d(TAG, "경로 요청 로딩중")
                    }

                    is DataResource.Success -> {
                        Log.d(TAG,"경로 거리 : ${state.data.distance}")
                        state.data.path.map {
                            Log.d(TAG,"경로 거리 : ${it.latitude}")
                            Log.d(TAG,"경로 거리 : ${it.longitutde}")
                        }
                        Log.d(TAG,"경로 거리 : ${state.data.departureTime}")

                        _pathData.value = state.data

                    }
                }
            }
        }
    }

    fun setLonggitutde(value : String) {
        _currentLongitude.value = value
    }

    fun setLatitude(value : String) {
        _currentLatitude.value = value
    }


    companion object{
        const val TAG = "NavigatePlaceViewModel"
    }
}