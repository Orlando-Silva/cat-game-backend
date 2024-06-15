package com.game.catbackend.domain.services

import com.game.catbackend.api.dto.request.JoinLobbyRequest
import com.game.catbackend.api.dto.response.AddLobbyResponse
import com.game.catbackend.api.dto.response.GetPlayersResponse
import com.game.catbackend.api.dto.response.JoinLobbyResponse
import com.game.catbackend.infra.repositories.LobbyRepository
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.entities.Player
import com.game.catbackend.domain.enums.LobbyStatus
import com.game.catbackend.domain.exceptions.CatGameLobbyFullException
import com.game.catbackend.domain.exceptions.CatGameLobbyNotFoundException
import com.game.catbackend.domain.exceptions.CatGameLobbyStatusException
import com.game.catbackend.domain.exceptions.CatGamePlayerAlreadyExistsException
import org.springframework.stereotype.Service
import java.util.*

@Service
class LobbyService(val lobbyRepository : LobbyRepository, val playerService: PlayerService) {

    fun get(id: Long): Optional<Lobby> {
        return lobbyRepository.findById(id)
    }

    fun addLobby(username: String): AddLobbyResponse {
        val lobby = lobbyRepository.save(
            Lobby(status = LobbyStatus.PENDING)
        )

        playerService.create(
            Player(username = username, lobbyId = lobby.id)
        )

        return AddLobbyResponse(lobby.roomId.toString())
    }

    fun joinLobby(roomId: UUID, joinLobbyRequest: JoinLobbyRequest) : JoinLobbyResponse {
        val lobby = lobbyRepository.findByRoomId(roomId).orElseThrow {
            CatGameLobbyNotFoundException("Lobby does not exist.")
        }

        if (lobby.status != LobbyStatus.PENDING) {
            throw CatGameLobbyStatusException("Lobby is not in PENDING status.")
        }

        val players = playerService.findPlayersByLobbyId(lobby.id)
        if (players.any { it.username == joinLobbyRequest.username }) {
            throw CatGamePlayerAlreadyExistsException("Player name already exists in lobby.")
        }

        if (players.size >= 8) {
            throw CatGameLobbyFullException("Lobby is full.")
        }

        playerService.addPlayer(joinLobbyRequest.username, lobby.id)

        return JoinLobbyResponse(
            roomId.toString(),
            players.map { p -> p.username }
        )
    }

    fun getPlayers(roomId: UUID) : GetPlayersResponse {
        val lobby = lobbyRepository.findByRoomId(roomId).orElseThrow {
            CatGameLobbyNotFoundException("Lobby does not exist.")
        }

        if (lobby.status != LobbyStatus.PENDING) {
            throw CatGameLobbyStatusException("Lobby is not in PENDING status.")
        }

        val players = playerService.findPlayersByLobbyId(lobby.id)

        return GetPlayersResponse(
            roomId.toString(),
            players.map { p -> p.username }
        )
    }
}