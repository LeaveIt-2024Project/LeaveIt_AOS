package com.example.leaveit.presentation.placeview.place.showplaceview

import com.example.leaveit.domain.model.PlaceDomainListModel
import com.example.leaveit.presentation.placeview.place.selectregionview.data.SelectRegionModelList
import kotlinx.coroutines.Deferred

interface ShowPlaceModelMapper {
    suspend fun toPlaceModel(temp : Deferred<PlaceDomainListModel>): SelectRegionModelList
}