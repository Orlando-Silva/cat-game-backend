package com.game.catbackend.domain.services

import com.game.catbackend.domain.entities.Player
import com.game.catbackend.infra.repositories.PlayerRepository
import com.game.catbackend.domain.exceptions.CatGameLobbyNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerService(val playerRepository: PlayerRepository) {

    fun findPlayersByLobbyId(lobbyId: Long): List<Player> {
        return playerRepository.findAllPlayersByLobbyId(lobbyId)
    }

    fun addPlayer(username: String, lobbyId: Long): Player {
        val newPlayer = Player(
            username = username,
            lobbyId = lobbyId
        )
        return playerRepository.save(newPlayer)
    }
}