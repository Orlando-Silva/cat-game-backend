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
            .withId(1)
            .build()
        lobbyRepository.save(lobby)

        val result = lobbyRepository.findById(1)

        assertThat(result.get().id).isEqualTo(lobby.id)
        assertThat(result.get().status).isEqualTo(lobby.status)
        assertThat(result.get().createdAt).isEqualTo(lobby.createdAt)
        assertThat(result.get().roomId).isEqualTo(lobby.roomId)
        assertThat(result.get().hostId).isEqualTo(lobby.hostId)
    }

}