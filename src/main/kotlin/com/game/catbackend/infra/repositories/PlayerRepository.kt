package com.game.catbackend.infra.repositories

import com.game.catbackend.domain.entities.Player
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository: JpaRepository<Player, Long> {


    fun findAllByLobbyId(lobbyId: Long): List<Player>
}