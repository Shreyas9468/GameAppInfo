package com.example.gameappinfo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gameappinfo.presentation.components.GameScreen
import com.example.gameappinfo.presentation.components.GamesScreen
import com.example.gameappinfo.presentation.viewmodel.GameViewModel

@Composable
fun Navigation(gameViewModel: GameViewModel , modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.GamesScreen.route) {
        composable(Screen.GamesScreen.route) {
            GamesScreen(
                gameViewModel = gameViewModel,
                onGameClick = { gameId ->
                    navController.navigate(Screen.GameScreen.createRoute(gameId))
                },
                modifier = modifier
            )
        }
        composable(
            route = Screen.GameScreen.route,
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId") ?: 112
            GameScreen(
               id =  gameId ,
                gameViewModel =gameViewModel,
                onBackClick = { navController.popBackStack() },
                modifier = modifier

            )
        }
    }
}
