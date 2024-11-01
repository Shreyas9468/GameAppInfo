package com.example.gameappinfo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameappinfo.core.common.Resource
import com.example.gameappinfo.domain.usecase.GameUseCase
import com.example.gameappinfo.domain.usecase.GamesUseCase
import com.example.gameappinfo.presentation.state.GameState
import com.example.gameappinfo.presentation.state.GamesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGamesUseCase: GameUseCase,
    private val getGameUseCase: GamesUseCase

): ViewModel() {
    private val _stategames = MutableStateFlow(GamesState())
    val stategames :StateFlow<GamesState>
    get() = _stategames

    private val _stategame = MutableStateFlow(GameState())
    val stategame :StateFlow<GameState>
    get() = _stategame

    init {
        getAllGames()
    }

    private fun getAllGames() {
        getGamesUseCase().onEach {
            when(it) {
                is Resource.Error -> {
                    _stategames.value = GamesState(error = it.msg ?: "Game is not loading thier is some fatal error please correct it")
                }
                is Resource.Loading ->{
                    _stategames.value = GamesState(isLoading = true)
                }
                is Resource.Success -> {

                    _stategames.value = GamesState(games = it.data ?: emptyList())

                }
            }
        }.launchIn(viewModelScope)
    }

    fun getGameById(id: Int) {
        getGameUseCase(id).onEach {
            when(it) {
                is Resource.Error -> {
                    _stategame.value = GameState(error = it.msg ?: "")
                }

                is Resource.Loading -> {
                    _stategame.value = GameState(isLoading = true)
                }
                is Resource.Success -> {
                    _stategame.value = GameState(game = it.data)

                }
            }
        }.launchIn(viewModelScope)
    }

}