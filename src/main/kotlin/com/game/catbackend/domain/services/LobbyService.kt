package com.game.catbackend.domain.services

import com.game.catbackend.infra.repositories.LobbyRepository
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.entities.Player
import com.game.catbackend.domain.enums.Status
import org.springframework.stereotype.Service
import java.util.*

@Service
class LobbyService(val lobbyRepository : LobbyRepository, val playerService: PlayerService) {

    fun get(id: Long): Optional<Lobby> {
        return lobbyRepository.findById(id)
    }

    fun addLobby(username: String): String {
        val lobby = Lobby()
        lobby.status = Status.PENDING
        lobbyRepository.save(lobby)

        val player = Player(username = username, lobbyId = lobby.id)
        playerService.create(player)

        return lobby.roomId.toString()
    }
}