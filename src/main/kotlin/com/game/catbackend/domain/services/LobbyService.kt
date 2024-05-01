package com.game.catbackend.domain.services

import com.game.catbackend.api.dto.JoinLobbyDTO
import com.game.catbackend.infra.repositories.LobbyRepository
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.entities.Player
import com.game.catbackend.domain.enums.Status
import com.game.catbackend.domain.enums.Status
import com.game.catbackend.domain.exceptions.CatGameLobbyFullException
import com.game.catbackend.domain.exceptions.CatGameLobbyNotFoundException
import com.game.catbackend.domain.exceptions.CatGameLobbyStatusException
import com.game.catbackend.domain.exceptions.CatGamePlayerAlreadyExistsException
import com.game.catbackend.domain.services.PlayerService
import org.springframework.stereotype.Service
import com.game.catbackend.domain.entities.Player
import java.util.*

@Service
class LobbyService(val lobbyRepository : LobbyRepository, val playerService: PlayerService) {

    fun get(id: Long): Optional<Lobby> {
        return lobbyRepository.findById(id)
    }

    fun addLobby(username: String): String {
        val lobby = Lobby()
        lobby.status = Status.PENDING
        lobbyRepository.save(lobby)

        val player = Player(username = username, lobbyId = lobby.id)
        playerService.create(player)

        return lobby.roomId.toString()
    }
    
    fun getLobbyByRoomId(roomId: UUID): Optional<Lobby> {
        return lobbyRepository.findByRoomId(roomId)
    }

    fun getPlayerUsernameListByRoomId(roomId: UUID): List<String> {
        val lobby = lobbyRepository.findByRoomId(roomId).orElseThrow { CatGameLobbyNotFoundException("Lobby does not exist.") }
        val playerList = playerService.findPlayersByLobbyId(lobby.id)
        return playerList.map { it.username }
    }

    fun joinLobby(roomId: UUID, joinLobbyDTO: JoinLobbyDTO) {
        val lobby = lobbyRepository.findByRoomId(roomId).orElseThrow { CatGameLobbyNotFoundException("Lobby does not exist.") }

        if (lobby.status != Status.PENDING) {
            throw CatGameLobbyStatusException("Lobby is not in PENDING status.")
        }

        val players = playerService.findPlayersByLobbyId(lobby.id)
        if (players.any { it.username == joinLobbyDTO.userName }) {
            throw CatGamePlayerAlreadyExistsException("Player name already exists in lobby.")
        }

        if (players.size >= 8) {
            throw CatGameLobbyFullException("Lobby is full.")
        }

        playerService.addPlayer(joinLobbyDTO.userName, lobby.id)
    }
}