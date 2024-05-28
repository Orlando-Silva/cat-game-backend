package com.game.catbackend.infra.repositories

import com.game.catbackend.CatBackendBaseTest
import com.game.catbackend.data.LobbyBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class LobbyRepositoryTests : CatBackendBaseTest() {

    @Autowired
    lateinit var lobbyRepository: LobbyRepository

    @BeforeEach
    fun cleanDatabase() {
        lobbyRepository.deleteAll()
    }

    @Test
    fun `when findById is called with existing id it should return the appropriate Lobby`() {
        val lobby = LobbyBuilder()
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val result = lobbyRepository.findById(persistedLobby.id)

        assertThat(result.get().id).isEqualTo(persistedLobby.id)
        assertThat(result.get().createdAt).isEqualTo(persistedLobby.createdAt)
        assertThat(result.get().status).isEqualTo(persistedLobby.status)
        assertThat(result.get().roomId).isEqualTo(persistedLobby.roomId)
    }

}