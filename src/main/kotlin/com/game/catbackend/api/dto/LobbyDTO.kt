package com.game.catbackend.api.dto

import jakarta.validation.constraints.Size

class LobbyDTO (
    @Size(min = 2, max = 50, message = "A username must be provided")
    val username: String = ""
)
