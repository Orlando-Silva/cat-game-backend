package com.game.catbackend.infra.repositories

import com.game.catbackend.domain.entities.Lobby
import jakarta.persistence.Lob
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface LobbyRepository : JpaRepository<Lobby, Long> {
    fun findByRoomId(roomId: UUID): Lobby

}