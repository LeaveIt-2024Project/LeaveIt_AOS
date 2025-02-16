package com.example.leaveit.domain.model

data class DetailCultureDomainModel(
    val contentId: String,
    val contenttypeid: String,
    val infocenterculture: String?,
    val restdateculture: String?,
    val chkpetculture: Boolean?,
    val parkingculture: Boolean?,
    val usetimeculture: String?,
    val chkbabycarriageculture: Boolean?,
    val usefee : String?
)
