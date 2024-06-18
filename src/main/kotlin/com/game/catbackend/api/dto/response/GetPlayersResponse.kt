package com.game.catbackend.api.dto.response

data class GetPlayersResponse (
    val roomId: String,
    val players: List<String>
)