package com.game.catbackend.api.dto.request

import jakarta.validation.constraints.Size


class JoinLobbyRequest(
    @Size(min = 1, max = 50, message = "Username must be filled")
    val userName: String
)