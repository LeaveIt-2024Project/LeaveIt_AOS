package com.example.leaveit.domain.repository

import com.example.leaveit.domain.model.PlaceDomainModel

interface PlaceRepository {
    //TODO 서버 측에서 피드 api 완성하면 구현하기

    suspend fun getPlaceViewRepository() : List<PlaceDomainModel>

}