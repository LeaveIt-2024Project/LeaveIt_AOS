package com.example.leaveit.presentation.placeview.place.navigateplaceview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.PathDomainModel
import com.example.leaveit.domain.usecase.path.GetPathUseCaseInterface
import com.example.leaveit.domain.usecase.reverse_geocoding.ReverseGeoCodingUseCase
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

@HiltViewModel
class NavigatePlaceViewModel @Inject constructor(
    private val getCurrentLocationAddress: ReverseGeoCodingUseCase,
    private val getPathUseCase: GetPathUseCaseInterface
) : ViewModel() {

    private val _currentAddress: MutableLiveData<String> by lazy { MutableLiveData() }
    val currentAddress: LiveData<String> = _currentAddress

    private val _currentLocation: MutableLiveData<LatLng> by lazy { MutableLiveData() }
    val currentLocation: LiveData<LatLng> = _currentLocation

    private val _pathData: MutableLiveData<PathDomainModel> by lazy { MutableLiveData() }
    val pathData: LiveData<PathDomainModel> = _pathData

    private val _centerLocation: MutableLiveData<LatLng> by lazy { MutableLiveData() }
    val centerLocation: LiveData<LatLng> = _centerLocation

    private val _allLocationData: MutableLiveData<NavigateDataClass> by lazy { MutableLiveData() }
    val allLocationData: LiveData<NavigateDataClass> = _allLocationData

    val locationDataForMap = MediatorLiveData<Pair<LatLng, Double>>().apply {
        addSource(centerLocation) { center ->
            val distance = pathData.value?.let { loc ->
                Log.d(TAG,"관광지와 현재 위치 간 거리 : $loc")
            }
            if (center != null && distance != null) {
                value = Pair(center, distance.toDouble())
            }
        }
        //haversine 값 유효한지 확인 후 줌 레벨 수정작업 중이였음 1/19
        addSource(pathData) { loc ->
            val center = centerLocation.value
            val zoomLevel = getZoomLevel(loc.distance)
            Log.d(TAG,"줌 레벨 :$zoomLevel")
            if (center != null) {
                value = Pair(center, zoomLevel.toDouble())
            }
        }
    }

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
                        Log.d(TAG, "현 위치 받아오기 성공")
                        Log.d(TAG, "현 위치 : ${state.data.address}")
                        _currentAddress.value = state.data.address
                    }
                }
            }
        }
    }

    fun getPath(start: String, goal: String) {
        viewModelScope.launch {
            getPathUseCase.getPath(start, goal).collect { state ->
                when (state) {
                    is DataResource.Error -> {
                        Log.e(TAG, state.throwable.toString())
                    }

                    is DataResource.Loading -> {
                        Log.d(TAG, "경로 요청 로딩중")
                    }

                    is DataResource.Success -> {
                        Log.d(TAG, "경로 도착 시간 : ${state.data.departureTime}")
                        Log.d(TAG, "경로 도착 시간 : ${state.data.distance}")
                        Log.d(TAG, "경로 요청 성공")
                        _pathData.value = state.data
                    }
                }
            }
        }
    }

    fun setCurrentLocation(lat: Double, lng: Double) {
        _currentLocation.value = LatLng(lat, lng)
    }


    // C((x1+x2)/2, (y1+y2)/2 ) 두 지점간 중앙 좌표 구하는 공식
    fun setCenterLocation(startLocation: LatLng, goalLocation: LatLng) {
        val start = (startLocation.latitude + goalLocation.latitude) / 2
        val goal = (startLocation.longitude + goalLocation.longitude) / 2
        _centerLocation.value = LatLng(start, goal)
    }

    fun setAllLocationData(
        currentLat: Double,
        currentLong: Double,
        placeLat: Double,
        placeLong: Double
    ) {
        _allLocationData.value = NavigateDataClass(
            currentLat = currentLat,
            currentLong = currentLong,
            placeLat = placeLong,
            placeLong = placeLong
        )
    }

    // Haversine 공식으로 두 좌표 간의 거리 계산 (단위: km)
    private fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371 // 지구 반지름 (단위: km)
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a =
            sin(dLat / 2).pow(2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(
                2
            )
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return R * c
    }

    //거리에 따른 카메라 줌 레벨 설정
    private fun getZoomLevel(distance: Int): Float {
        return when {
            distance < 1 -> 14f // 1km 이하: 가장 확대
            distance < 5 -> 13f
            distance < 10 -> 12f
            distance < 20 -> 11f
            distance < 50 -> 10f
            distance < 100 -> 9f
            distance < 200 -> 8f
            distance < 500 -> 7f
            distance < 1000 -> 6f
            else -> 6f // 1000km 이상: 가장 축소
        }
    }

    companion object {
        const val TAG = "NavigatePlaceViewModel"
    }
}