package com.example.leaveit.data.model

import com.example.leaveit.data.placeview.hotkeyowrd.HotKeyWordPalceMapper
import com.example.leaveit.domain.model.HotKeyWordDomain
import com.example.leaveit.domain.model.HotKeyWordDomainModel

data class HotKeyWordDataModel(
    val data: List<HotKeyWordData>
) : HotKeyWordPalceMapper {
    override fun toDomain(): HotKeyWordDomainModel {
        val data = data
        var count = 9
        var number = 0 // 인기 검색어 순위 설정할 변수
        // data를 하나씩 순회하면서 10개를 다 채우는데 만약 없다면 number만 채우기
        while (count > 0) {
            data[count].number = (count+1).toString()
            count--
        }

        return HotKeyWordDomainModel(
            data.map {
                number++
                HotKeyWordDomain(
                    title = it.title,
                    number = number
                )
            }
        )
    }
}

data class HotKeyWordData(
    var number: String,
    val title: String
)