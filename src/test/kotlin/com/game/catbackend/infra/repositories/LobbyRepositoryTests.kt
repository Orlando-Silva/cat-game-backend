package com.game.catbackend.infra.repositories

import com.game.catbackend.CatBackendBaseTest
import com.game.catbackend.data.LobbyBuilder
import com.game.catbackend.data.PlayerBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class LobbyRepositoryTests : CatBackendBaseTest() {

    @Autowired
    lateinit var lobbyRepository: LobbyRepository
    @Autowired
    lateinit var playerRepository: PlayerRepository

    @BeforeEach
    fun cleanDatabase() {
        lobbyRepository.deleteAll()
    }

    @Test
    fun `when findById is called with existing id it should return the appropriate Lobby`() {
        val hostPlayer = PlayerBuilder()
            .withUsername("host")
            .build()

        playerRepository.save(hostPlayer)

        val lobby = LobbyBuilder()
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val result = lobbyRepository.findById(persistedLobby.id)

        assertThat(result.get().id).isEqualTo(persistedLobby.id)
        assertThat(result.get().createdAt).isEqualTo(persistedLobby.createdAt)
        assertThat(result.get().status).isEqualTo(persistedLobby.status)
        assertThat(result.get().roomId).isEqualTo(persistedLobby.roomId)
    }

    @Test
    fun `when findByRoomId is called with existing roomId it should return the appropriate Lobby`() {
        val hostPlayer = PlayerBuilder()
            .withUsername("host")
            .build()
        playerRepository.save(hostPlayer)


        val lobby = LobbyBuilder()
            .withRoomId(UUID.randomUUID())
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val result = lobbyRepository.findByRoomId(persistedLobby.roomId)

        assertThat(result.isPresent).isTrue
        assertThat(result.get().roomId).isEqualTo(persistedLobby.roomId)
    }

    @Test
    fun `when findIdByRoomId is called with existing roomId it should return the correct Id`() {
        val hostPlayer = PlayerBuilder()
            .withUsername("host")
            .build()
        playerRepository.save(hostPlayer)

        val lobby = LobbyBuilder()
            .withRoomId(UUID.randomUUID())
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val result = lobbyRepository.findByRoomId(persistedLobby.roomId)

        assertThat(result.get().id).isNotNull
        assertThat(result.get().id).isEqualTo(persistedLobby.id)
    }

    @Test
    fun `when findByRoomId is called with non-existing roomId it should return empty`() {
        val result = lobbyRepository.findByRoomId(UUID.randomUUID())

        assertThat(result.isPresent).isFalse
    }

}