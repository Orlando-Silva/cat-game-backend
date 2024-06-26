package com.game.catbackend.domain.services

import com.game.catbackend.domain.entities.Player
import com.game.catbackend.infra.repositories.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(val playerRepository: PlayerRepository) {

    fun create(player: Player): Player {
        return playerRepository.save(player)
    }
    
    fun findPlayersByLobbyId(lobbyId: Long): List<Player> {
        return playerRepository.findAllByLobbyId(lobbyId)
    }

    fun addPlayer(username: String, lobbyId: Long): Player {
        val newPlayer = Player(
            username = username,
            lobbyId = lobbyId
        )
        return playerRepository.save(newPlayer)
    }
}