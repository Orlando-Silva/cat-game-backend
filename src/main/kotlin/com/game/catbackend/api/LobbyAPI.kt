package com.game.catbackend.api

import com.game.catbackend.api.dto.JoinLobbyDTO
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.services.LobbyService
import com.game.catbackend.api.dto.response.AddLobbyDTO
import com.game.catbackend.api.dto.LobbyDTO
import com.game.catbackend.api.dto.response.JoinLobbyResponse
import jakarta.validation.Valid
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
    fun joinLobby(@PathVariable roomId: UUID, @RequestBody joinLobbyDTO: JoinLobbyDTO): ResponseEntity<JoinLobbyResponse> {
        return ResponseEntity.ok(lobbyService.joinLobby(roomId, joinLobbyDTO))
    }
}
