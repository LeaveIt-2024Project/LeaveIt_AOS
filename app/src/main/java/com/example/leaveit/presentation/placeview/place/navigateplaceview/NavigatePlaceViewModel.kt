package com.example.leaveit.presentation.placeview.place.navigateplaceview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.usecase.reverse_geocoding.ReverseGeoCodingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigatePlaceViewModel @Inject constructor(
    private val getCurrentLocationAddress: ReverseGeoCodingUseCase
) : ViewModel() {

    private val _currentAddress: MutableLiveData<String> by lazy { MutableLiveData() }
    val currentAddress: LiveData<String> = _currentAddress

    private val _currentLongitude: MutableLiveData<String> by lazy { MutableLiveData() }
    val currentLongitude: LiveData<String> = _currentLongitude

    private val _currentLatitude: MutableLiveData<String> by lazy { MutableLiveData() }
    val currentLatitude : LiveData<String> = _currentLatitude

    fun getCurrentLocationAddress(value: String) {
        viewModelScope.launch {
            getCurrentLocationAddress.getReverseGeoCodingData(value).collect { state ->
                when (state) {
                    is DataResource.Error -> {
                        Log.e(TAG, state.throwable.toString())
                    }

                    is DataResource.Loading -> {
                        Log.d(TAG, "로딩중")
                    }

                    is DataResource.Success -> {
                        _currentAddress.value = state.data.address
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