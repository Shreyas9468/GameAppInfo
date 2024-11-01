package com.example.gameappinfo.presentation.state

import com.example.gameappinfo.domain.model.Games

data class GamesState (
    val isLoading: Boolean = false,
    val games: List<Games> ?= emptyList(),
    val error: String ?= ""
)

