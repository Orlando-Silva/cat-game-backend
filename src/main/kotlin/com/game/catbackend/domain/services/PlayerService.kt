package com.game.catbackend.domain.services

import com.game.catbackend.domain.entities.Player
import com.game.catbackend.infra.repositories.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(val playerRepository: PlayerRepository) {

    fun create(player: Player): Player {
        return playerRepository.save(player)
    }
}