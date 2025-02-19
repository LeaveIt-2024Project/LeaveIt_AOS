package com.example.leaveit.presentation.myprofile

data class MyProfileChangeModel(
    var id: String,
    var region1: String, // 화면에 보여주는 지역명
    var region2: String // 검색에 쓸 지역명(아직 사용안함)
) {
    override fun toString(): String{
        return region1
    }
}
