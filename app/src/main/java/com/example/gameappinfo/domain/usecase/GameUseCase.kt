package com.example.gameappinfo.domain.usecase

import com.example.gameappinfo.domain.repository.GameRepository
import javax.inject.Inject

class GameUseCase @Inject constructor(
   private val repository: GameRepository
) {
    operator fun invoke() = repository.getGames()

}