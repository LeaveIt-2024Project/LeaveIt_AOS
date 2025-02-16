package com.example.leaveit.presentation.placeview.place.detailview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailCultureDomainModel
import com.example.leaveit.domain.model.DetailFestivalDomainModel
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import com.example.leaveit.domain.usecase.place.detailplace.DetailPlaceUseCaseInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailFeedData: DetailPlaceUseCaseInterface
) : ViewModel() {

    private val _detailPlaceData: MutableLiveData<DetailPlaceDomainModel> by lazy { MutableLiveData() }
    val detailPlaceData: LiveData<DetailPlaceDomainModel> = _detailPlaceData

    private val _detailCultureData: MutableLiveData<DetailCultureDomainModel> by lazy { MutableLiveData() }
    val detailCultureData: LiveData<DetailCultureDomainModel> = _detailCultureData

    private val _detailFestivalData: MutableLiveData<DetailFestivalDomainModel> by lazy { MutableLiveData() }
    val detailFestivalData: LiveData<DetailFestivalDomainModel> = _detailFestivalData

    fun getDetailPlaceData(id: String,type : String) {
        viewModelScope.launch {
            getDetailFeedData.getDetailPlaceData(id,type).collect { state ->
                when (state) {
                    is DataResource.Error -> {
                        Log.e(TAG, state.throwable.toString())
                    }

                    is DataResource.Loading -> {
                        Log.d(TAG, "로딩중")
                    }

                    is DataResource.Success -> {
                        _detailPlaceData.value = state.data
                    }

                }
            }
        }
    }

    fun getDetailCultureData(id: String,type : String) {
        viewModelScope.launch {
            getDetailFeedData.getDetailCultureData(id,type).collect { state ->
                when (state) {
                    is DataResource.Error -> {
                        Log.e(TAG, state.throwable.toString())
                    }

                    is DataResource.Loading -> {
                        Log.d(TAG, "로딩중")
                    }

                    is DataResource.Success -> {
                        _detailCultureData.value = state.data
                    }

                }
            }
        }
    }

    fun getDetailFestivalData(id: String,type : String) {
        viewModelScope.launch {
            getDetailFeedData.getDetailFestivalData(id,type).collect { state ->
                when (state) {
                    is DataResource.Error -> {
                        Log.e(TAG, state.throwable.toString())
                    }

                    is DataResource.Loading -> {
                        Log.d(TAG, "로딩중")
                    }

                    is DataResource.Success -> {
                        _detailFestivalData.value = state.data
                    }

                }
            }
        }
    }




    companion object {
        const val TAG = "DetailPlaceViewModel"
    }

}
