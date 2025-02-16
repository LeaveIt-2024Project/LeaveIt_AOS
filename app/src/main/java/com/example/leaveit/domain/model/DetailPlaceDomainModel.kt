package com.example.leaveit.domain.model

data class DetailPlaceDomainModel(
    val contentId: String,
    val contenttypeid: String,
    val infocenter: String?,
    val restdate: String?,
    val chkpet: Boolean?,
    val chkbabycarriage : Boolean?,
    val parking: Boolean?,
    val usetime: String?
)
