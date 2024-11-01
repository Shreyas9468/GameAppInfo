package com.example.gameappinfo.data.repository

import com.example.gameappinfo.core.common.Resource
import com.example.gameappinfo.data.api.GameApi
import com.example.gameappinfo.data.mapper.toDomainGame
import com.example.gameappinfo.data.mapper.toDomainGames
import com.example.gameappinfo.domain.model.Game
import com.example.gameappinfo.domain.model.Games
import com.example.gameappinfo.domain.repository.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val api: GameApi
)  : GameRepository {
    override fun getGames(): Flow<Resource<List<Games>>>  = flow {
        emit(Resource.Loading())
        val games = api.getGames().map {
                it.toDomainGames()
        }
        emit(Resource.Success(games))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message ?: "Something went wrong"))
        }

    override fun getGameById(id: Int): Flow<Resource<Game>> = flow {
        emit(Resource.Loading())

        val game = api.getGameById(id).toDomainGame()

        emit(Resource.Success(game))

    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message ?: "Something went wrong"))
        }
}