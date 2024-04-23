package com.game.catbackend.domain.services

import com.game.catbackend.CatBackendBaseTest
import com.game.catbackend.data.LobbyBuilder
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
            .withId(1)
            .build()
        lobbyRepository.save(lobby)

        val result = lobbyService.get(1)

        assertThat(result.get().id).isEqualTo(lobby.id)
        assertThat(result.get().status).isEqualTo(lobby.status)
        assertThat(result.get().createdAt).isEqualTo(lobby.createdAt)
        assertThat(result.get().roomId).isEqualTo(lobby.roomId)
        assertThat(result.get().hostId).isEqualTo(lobby.hostId)
    }

}
