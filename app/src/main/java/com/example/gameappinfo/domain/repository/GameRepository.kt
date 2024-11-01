package com.example.gameappinfo.domain.repository

import com.example.gameappinfo.core.common.Resource
import com.example.gameappinfo.domain.model.Game
import com.example.gameappinfo.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface GameRepository {

     fun getGames(): Flow<Resource<List<Games>>>

     fun getGameById(id: Int):Flow<Resource<Game>>


}