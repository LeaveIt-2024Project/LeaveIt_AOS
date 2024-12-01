package com.example.leaveit.domain.usecase

import com.example.leaveit.domain.model.PlaceDomainListModel
import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModelList
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class GetPlaceUseCase @Inject constructor() : GetPlaceUseCaseInterface {

    @Inject
    lateinit var bindPlaceRepository: GetPlaceRepositoryInterface

    override suspend fun getPlaceUseCase(
        contentTypedId: String,
        areaCode: String
    ): SelectRegionModelList = runBlocking {

        // PlaceViewRepositoryImpl에서 데이터 받아오기
        val temp: Deferred<PlaceDomainListModel> = async(Dispatchers.IO) {
            val response = bindPlaceRepository.getPlaceRepository(
                contentTypedId = contentTypedId,
                areaCode = areaCode
            )

            return@async response
        }

        val result = temp.await().toPlaceModel(temp)

        return@runBlocking result
    }


    companion object {
        val TAG = "GetPlaceUseCase"
    }

}