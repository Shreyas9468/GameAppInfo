package com.example.gameappinfo.data.mapper

import com.example.gameappinfo.data.dto.GamesDto
import com.example.gameappinfo.domain.model.Games

fun GamesDto.toDomainGames(): Games {
    return Games(
        genre = genre,
        id = id,
        release_date = releaseDate,
        short_description = shortDescription,
        thumbnail = thumbnail,
        title = title
    )
}