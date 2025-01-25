package com.example.leaveit.presentation.placeview.restraunt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leaveit.dataResource.DataResource
import com.example.leaveit.domain.model.DetailRestrauntDomainModel
import com.example.leaveit.domain.model.RestrantListDomainModel
import com.example.leaveit.domain.model.RestrauntDomainModel
import com.example.leaveit.domain.usecase.detail_restraunt.DetailRestrauntUseCase
import com.example.leaveit.domain.usecase.restraunt.RestrauntUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestrauntViewModel @Inject constructor(
    private val bindRestrauntUseCaseImpl: RestrauntUseCase,
    private val bindDetailRestrauntUseCaseImpl: DetailRestrauntUseCase
) : ViewModel() {

    private val _restrauntList : MutableLiveData<RestrantListDomainModel> by lazy { MutableLiveData() }
    val restrauntList : LiveData<RestrantListDomainModel> = _restrauntList

    private val _selectRestrauntData : MutableLiveData<RestrauntDomainModel> by lazy { MutableLiveData() }
    val selectRestrauntData : LiveData<RestrauntDomainModel> = _selectRestrauntData

    private val _data : MutableLiveData<DetailRestrauntDomainModel> by lazy { MutableLiveData() }
    val data : LiveData<DetailRestrauntDomainModel> = _data


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
    fun getDetailRestrauntData(contentId : String){
        viewModelScope.launch {
            bindDetailRestrauntUseCaseImpl.getData(contentId).collect{state ->
                when(state){
                    is DataResource.Error -> Log.e(TAG,state.throwable.message.toString())
                    is DataResource.Loading -> Log.d(TAG,"음식점 정보 불러오는 중")
                    is DataResource.Success -> {
                        _data.value = state.data
                    }
                }
            }
        }
    }

    fun setRestrauntData(value : RestrauntDomainModel ){
        _selectRestrauntData.value = value
    }

    companion object{
        const val TAG = "RestrauntViewModel"
    }
}