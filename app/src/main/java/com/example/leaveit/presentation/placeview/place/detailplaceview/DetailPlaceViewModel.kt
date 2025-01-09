package com.example.leaveit.presentation.placeview.place.detailplaceview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailPlaceDomainModel
import com.example.leaveit.domain.usecase.review.detailplace.DetailPlaceUseCaseInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlaceViewModel @Inject constructor(
    private val getDetailFeedData: DetailPlaceUseCaseInterface
) : ViewModel() {

    private val _detailPlaceData: MutableLiveData<DetailPlaceDomainModel> by lazy { MutableLiveData() }
    val detailPlaceData: LiveData<DetailPlaceDomainModel> = _detailPlaceData


    fun getDetailPlaceData(value: String) {
        viewModelScope.launch {
            getDetailFeedData.getDetailPlaceData(value).collect { state ->
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


    companion object {
        const val TAG = "DetailPlaceViewModel"
    }

}
