package com.game.catbackend.api

import com.game.catbackend.api.dto.response.AddLobbyDTO
import com.game.catbackend.api.dto.LobbyDTO
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.services.LobbyService
import jakarta.validation.Valid
import com.game.catbackend.api.dto.JoinLobbyDTO
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.services.LobbyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/lobby")
class LobbyAPI(val lobbyService: LobbyService) {

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Optional<Lobby> {
        return lobbyService.get(id)
    }

    @PostMapping
    fun addLobby(@RequestBody @Valid lobbyDTO: LobbyDTO): AddLobbyDTO {
        return AddLobbyDTO(lobbyService.addLobby(lobbyDTO.username))
    }

    @PostMapping("/{roomId}")
    fun joinLobby(@PathVariable roomId: UUID, @RequestBody joinLobbyDTO: JoinLobbyDTO): ResponseEntity<Map<String, Any>> {
        lobbyService.joinLobby(roomId, joinLobbyDTO)
        val playerList = lobbyService.getPlayerUsernameListByRoomId(roomId)
        val response = mapOf(
            "roomId" to roomId.toString(),
            "playerList" to playerList
        )
        return ResponseEntity.ok(response)
    }
}
