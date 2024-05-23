package com.game.catbackend.domain.services

import com.game.catbackend.CatBackendBaseTest
import com.game.catbackend.data.LobbyBuilder
import com.game.catbackend.domain.enums.Status
import com.game.catbackend.infra.repositories.LobbyRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class LobbyServiceTests : CatBackendBaseTest() {

    @Autowired
    private lateinit var lobbyService: LobbyService

    @Autowired
    private lateinit var lobbyRepository: LobbyRepository

    @BeforeEach
    fun cleanDatabase() {
        lobbyRepository.deleteAll()
    }

    @Test
    fun `when get is called with existing id it should return the appropriate Lobby`() {
        val lobby = LobbyBuilder()
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val result = lobbyService.get(persistedLobby.id)

        assertThat(result.get().id).isEqualTo(persistedLobby.id)
        assertThat(result.get().createdAt).isEqualTo(persistedLobby.createdAt)
        assertThat(result.get().status).isEqualTo(persistedLobby.status)
        assertThat(result.get().roomId).isEqualTo(persistedLobby.roomId)
    }

    @Test
    fun `when addLobby is called it should create a new lobby with Pending status and a new player register and return the roomId`() {
        val username = "alan"

        val roomId = lobbyService.addLobby(username)

        val createdLobby = lobbyRepository.findByRoomId(UUID.fromString(roomId))

        // TODO: Player test

        assertThat(createdLobby).isNotNull
        assertThat(createdLobby.status).isEqualTo(Status.PENDING)
        assertThat(createdLobby.roomId.toString()).isEqualTo(roomId)

    }

}
