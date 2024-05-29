package com.game.catbackend.domain.services

import com.game.catbackend.CatBackendBaseTest
import com.game.catbackend.api.dto.request.JoinLobbyRequest
import com.game.catbackend.data.LobbyBuilder
import com.game.catbackend.data.PlayerBuilder
import com.game.catbackend.domain.enums.LobbyStatus
import com.game.catbackend.infra.repositories.LobbyRepository
import com.game.catbackend.infra.repositories.PlayerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class LobbyServiceTests : CatBackendBaseTest() {

    @Autowired
    private lateinit var lobbyService: LobbyService

    @Autowired
    private lateinit var lobbyRepository: LobbyRepository

    @Autowired
    private lateinit var playerService: PlayerService

    @Autowired
    private lateinit var playerRepository: PlayerRepository


    @BeforeEach
    fun cleanDatabase() {
        lobbyRepository.deleteAll()
        playerRepository.deleteAll()
    }

    @Test
    fun `when get is called with existing id it should return the appropriate Lobby`() {
        val hostPlayer = PlayerBuilder()
            .withUsername("host")
            .build()
        val persistedPlayer = playerRepository.save(hostPlayer)

        val lobby = LobbyBuilder()
            .withRoomId(UUID.randomUUID())
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val result = lobbyService.get(persistedLobby.id)

        assertThat(result.isPresent).isTrue
        assertThat(result.get().id).isEqualTo(persistedLobby.id)
        assertThat(result.get().status).isEqualTo(persistedLobby.status)
        assertThat(result.get().createdAt).isEqualTo(persistedLobby.createdAt)
        assertThat(result.get().roomId).isEqualTo(persistedLobby.roomId)
    }

    @Test
    fun `when addLobby is called it should create a new lobby with Pending status and a new player register and return the roomId`() {
        val username = "host"

        val addedLobby = lobbyService.addLobby(username)

        val createdLobby = lobbyRepository.findByRoomId(UUID.fromString(addedLobby.roomId)).get()

        assertThat(createdLobby).isNotNull
        assertThat(createdLobby.status).isEqualTo(LobbyStatus.PENDING)

        val hostPlayer = PlayerBuilder()
            .withUsername("host")
            .build()
        val persistedPlayer = playerRepository.save(hostPlayer)

        val lobby = LobbyBuilder()
            .withRoomId(UUID.randomUUID())
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val result = lobbyService.get(persistedLobby.id)

        assertThat(result.isPresent).isTrue
        assertThat(result.get().id).isEqualTo(persistedLobby.id)
        assertThat(result.get().status).isEqualTo(persistedLobby.status)
        assertThat(result.get().createdAt).isEqualTo(persistedLobby.createdAt)
        assertThat(result.get().roomId).isEqualTo(persistedLobby.roomId)
    }

    @Test
    fun `when joinLobby is called with valid conditions it should add a player`() {
        val hostPlayer = PlayerBuilder()
            .withUsername("host")
            .build()
        val persistedPlayer = playerRepository.save(hostPlayer)

        val lobby = LobbyBuilder()
            .withRoomId(UUID.randomUUID())
            .withStatus(LobbyStatus.PENDING)
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val joinLobbyRequest = JoinLobbyRequest("Player 9")

        lobbyService.joinLobby(persistedLobby.roomId, joinLobbyRequest)

        assertThat(playerService.findPlayersByLobbyId(persistedLobby.id).size).isEqualTo(1)
    }

    @Test
    fun `when joinLobby is called for a full lobby it should throw an exception`() {
        val hostPlayer = PlayerBuilder()
            .withUsername("host")
            .build()
        val persistedPlayer = playerRepository.save(hostPlayer)

        val lobby = LobbyBuilder()
            .withRoomId(UUID.randomUUID())
            .withStatus(LobbyStatus.PENDING)
            .build()
        val persistedLobby = lobbyRepository.save(lobby)

        val players =
            List(8) { PlayerBuilder().withLobbyId(persistedLobby.id).build().also { playerRepository.save(it) } }
        println("Size:")
        println(players.size)
        println("Generated ids:")
        players.forEach{println("${it.id}  ${it.username}")}

        val playersInLobby = playerRepository.findAllByLobbyId(persistedLobby.id)
        println("DB Size:")
        println(playersInLobby.size)
        println("Oi")
        playersInLobby.forEach{println("${it.id}  ${it.username}")}

        val joinLobbyRequest = JoinLobbyRequest("Player 9")

        val exception = assertThrows<Exception> {
            lobbyService.joinLobby(persistedLobby.roomId, joinLobbyRequest)
        }

        assertThat(exception.message).isEqualTo("Lobby is full.")
    }
}
