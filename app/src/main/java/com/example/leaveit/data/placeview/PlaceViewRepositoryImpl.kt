package com.example.leaveit.data.placeview

import com.example.leaveit.data.model.PlaceViewDataModelList
import com.example.leaveit.domain.model.PlaceDomainListModel
import com.example.leaveit.domain.usecase.GetPlaceRepositoryInterface
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class PlaceViewRepositoryImpl @Inject constructor() : GetPlaceRepositoryInterface {
    @Inject
    lateinit var bindPlaceViewDataSourceImpl: PlaceViewDataSourceInterface


    override suspend fun getPlaceRepository(
        contentTypedId: String,
        areaCode: String
    ): PlaceDomainListModel = runBlocking {

        //PlaceViewDataSoureImpl에서 데이터 받아오기
        val temp: Deferred<PlaceViewDataModelList> = async(Dispatchers.IO) {
            val response = bindPlaceViewDataSourceImpl.getPlaceView(
                contentTypedId = contentTypedId,
                areaCode = areaCode
            )

            return@async response
        }

        // 요청한 데이터 받아올때까지 await()함수로 기다리는 비동기 처리
        val result = temp.await().toDomain(temp)

        // 요청한 값 반환
        return@runBlocking result
    }

}