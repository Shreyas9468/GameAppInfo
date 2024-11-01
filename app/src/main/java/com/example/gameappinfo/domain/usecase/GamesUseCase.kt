package com.example.gameappinfo.domain.usecase

import com.example.gameappinfo.domain.repository.GameRepository
import javax.inject.Inject

class GamesUseCase @Inject constructor(
    val repository: GameRepository
){

    operator fun invoke(id: Int) = repository.getGameById(id)
}