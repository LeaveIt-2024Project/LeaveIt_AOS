package com.example.leaveit.remote.entity

data class testdto(
    val response: response
)


data class bodys(
    val items: items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class items(
    val item: List<item>
)

data class item(
    val addr1: String,
    val addr2: String?,
    val areacode: String,
    val booktour: String,
    val cat1: String,
    val cat2: String,
    val cat3: String,
    val contentid: String,
    val contenttypeid: String,
    val createdtime: String,
    val firstimage: String?,
    val firstimage2: String?,
    val cpyrhtDivCd: String?,
    val mapx: String,
    val mapy: String,
    val mlevel: String,
    val modifiedtime: String,
    val sigungucode: String,
    val tel: String?,
    val title: String,
    val zipcode: String
)