package com.game.catbackend.domain.services

import com.game.catbackend.infra.repositories.LobbyRepository
import com.game.catbackend.domain.entities.Lobby
import org.springframework.stereotype.Service
import java.util.*

@Service
class LobbyService(val lobbyRepository : LobbyRepository) {

    fun get(id: Long): Optional<Lobby> {
        return lobbyRepository.findById(id)
    }

}