package com.example.leaveit.remote.entity

data class SearchEntity(
    val items: List<SearchItem>
)

data class SearchItem(
    val title: String,
    val link: String,
    val category: String,
    val description: String,
    val telephone: String,
    val address: String,
    val mapx: Double,
    val mapy: Double
)