package com.example.leaveit.remote.entity

data class LikeEntitiy(
    val feedUID : String,
    val kaKaoUID : String?,
    val userUID : String?,
    val isUserLiked : Boolean?
)