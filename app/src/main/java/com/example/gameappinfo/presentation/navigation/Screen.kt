package com.example.gameappinfo.presentation.navigation

sealed class Screen(val route: String) {
    object GamesScreen : Screen("games_screen")
    object GameScreen : Screen("game_screen/{gameId}") {
        fun createRoute(gameId: Int) = "game_screen/$gameId"
    }
}
