package com.example.leaveit.domain.model

data class HotKeyWordDomainModel(
    val data : List<HotKeyWordDomain>
)

data class HotKeyWordDomain(
    val number : Int,
    val title : String
)