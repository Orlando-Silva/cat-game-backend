package com.game.catbackend.api.dto.request

import jakarta.validation.constraints.Size

class AddLobbyRequest (
    @Size(min = 2, max = 50, message = "A username must be provided")
    val username: String
)
