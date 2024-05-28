package com.game.catbackend.api.dto.response

data class JoinLobbyResponse (
    val roomId: String,
    val players: List<String>
)