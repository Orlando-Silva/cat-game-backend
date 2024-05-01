package com.game.catbackend.infra.repositories

import com.game.catbackend.domain.entities.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PlayerRepository: JpaRepository<Player, Long> {


    fun findAllPlayersByLobbyId(lobbyId: Long): List<Player>
}