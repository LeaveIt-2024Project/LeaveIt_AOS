package com.example.leaveit.remote.placeview

import android.util.Log
import com.example.leaveit.BuildConfig
import com.example.leaveit.data.model.PlaceViewDataModelList
import com.example.leaveit.data.placeview.PlaceViewDataSourceInterface
import com.example.leaveit.remote.api.RetrofitService
import com.example.leaveit.remote.entity.firstResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PlaceViewDataSourceImpl @Inject constructor() : PlaceViewDataSourceInterface {

    @Inject
    lateinit var service: RetrofitService

    override suspend fun getPlaceView(contentTypedId : String, areaCode : String): PlaceViewDataModelList = runBlocking {

        try {
            //데이터 받아오기
            val temp : Deferred<firstResponse> = async(Dispatchers.IO) {
                val response = service.retrofitService.getSortRegionItemFlow(
                    "AND",
                    "LeaveIt",
                    BuildConfig.TOUR_API_KEY,
                    contentTypedId = contentTypedId,
                    areaCode = areaCode
                )

                return@async response
            }

            //서버 응답 전체를 받아옴
            val response = temp.await().response

            // 응답 상태 확인
            // 응답문이 없거나 결과코드가 0000(정상코드)가 아닌경우 Exception 던짐
            if (response == null || response.header.resultCode != "0000") {
                Log.e(TAG, "API 호출 실패: ${response.header.resultMsg}")
                throw Exception("API 호출 실패: ${response.header.resultMsg}")
            }

             //Data Layer의 모델로 변경 후 반환
            return@runBlocking response.body.placeEntityList.toData(response)
        }catch (e : Exception){
            Log.e(TAG,"에러 발생 : ${e.message.toString()}")
            throw e
        }
    }

    companion object{
        val TAG = "PlaceViewDataSourceImpl"
    }

}