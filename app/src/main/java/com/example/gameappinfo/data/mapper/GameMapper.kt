package com.example.gameappinfo.data.mapper

import com.example.gameappinfo.data.dto.GamesDto
import com.example.gameappinfo.domain.model.Game

fun GamesDto.toDomainGame() : Game {
    return  Game(
        developer = developer,
        freetogame_profile_url = freeToGameProfileUrl,
        game_url = gameUrl,
        genre = genre,
        id = id,
        platform = platform,
        publisher = publisher,
        release_date = releaseDate,
        short_description = shortDescription,
        thumbnail = thumbnail,
        title = title
    )
}