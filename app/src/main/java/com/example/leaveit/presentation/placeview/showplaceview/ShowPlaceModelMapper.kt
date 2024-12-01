package com.example.leaveit.presentation.placeview.showplaceview

import com.example.leaveit.domain.model.PlaceDomainListModel
import com.example.leaveit.presentation.placeview.selectregionview.data.SelectRegionModelList
import kotlinx.coroutines.Deferred

interface ShowPlaceModelMapper {
    suspend fun toPlaceModel(temp : Deferred<PlaceDomainListModel>): SelectRegionModelList
}