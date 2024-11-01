package com.example.gameappinfo.data.api

import com.example.gameappinfo.data.dto.GamesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://www.freetogame.com/api/games
interface GameApi {
    @GET("games")
    suspend fun getGames(): List<GamesDto>

    @GET("game")
    suspend fun getGameById(@Query("id") id: Int): GamesDto
}