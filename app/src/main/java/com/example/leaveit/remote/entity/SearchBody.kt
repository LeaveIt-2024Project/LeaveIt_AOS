package com.example.leaveit.remote.entity

import com.google.gson.annotations.SerializedName

data class SearchBody(
    @SerializedName("title") val title : String?
)