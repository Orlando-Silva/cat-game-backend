package com.game.catbackend.api.dto

import jakarta.validation.constraints.Size


class JoinLobbyDTO(
    @Size(min = 1, max = 50, message = "Username must be filled")
    val userName: String
)