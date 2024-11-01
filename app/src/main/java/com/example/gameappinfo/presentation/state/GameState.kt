package com.example.gameappinfo.presentation.state

import com.example.gameappinfo.domain.model.Game

data class GameState (
    val isLoading: Boolean = false,
    val game: Game? = null,
    val error: String ?= ""
)
