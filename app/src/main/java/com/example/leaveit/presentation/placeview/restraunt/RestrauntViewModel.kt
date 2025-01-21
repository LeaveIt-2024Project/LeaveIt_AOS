package com.example.leaveit.presentation.placeview.restraunt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.RestrantListDomainModel
import com.example.leaveit.domain.usecase.restraunt.RestrauntUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestrauntViewModel @Inject constructor(
    private val bindRestrauntUseCaseImpl: RestrauntUseCase
) : ViewModel() {

    private val _restrauntList : MutableLiveData<RestrantListDomainModel> by lazy { MutableLiveData() }
    val restrauntList : LiveData<RestrantListDomainModel> = _restrauntList



    fun getRestrauntList(longitude : String, latitude : String){
       viewModelScope.launch {
           bindRestrauntUseCaseImpl.getRestrauntData(longitude,latitude).collect{state ->
                when(state){
                    is DataResource.Error -> Log.e(TAG,state.throwable.message.toString())
                    is DataResource.Loading -> Log.d(TAG,"음식점 정보 불러오는 중")
                    is DataResource.Success -> {
                        _restrauntList.value = state.data
                    }
                }
           }
       }
    }

    companion object{
        const val TAG = "RestrauntViewModel"
    }
}