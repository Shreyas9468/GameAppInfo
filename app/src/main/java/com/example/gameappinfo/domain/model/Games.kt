package com.example.gameappinfo.domain.model

data class Games(
    val genre: String,
    val id: Int,
    val release_date: String,
    val short_description: String,
    val thumbnail: String,
    val title: String
)