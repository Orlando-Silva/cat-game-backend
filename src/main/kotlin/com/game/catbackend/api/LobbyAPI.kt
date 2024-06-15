package com.game.catbackend.api

import com.game.catbackend.api.dto.request.JoinLobbyRequest
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.services.LobbyService
import com.game.catbackend.api.dto.response.AddLobbyResponse
import com.game.catbackend.api.dto.request.AddLobbyRequest
import com.game.catbackend.api.dto.response.GetPlayersResponse
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
    fun add(@RequestBody @Valid addLobbyRequest: AddLobbyRequest): ResponseEntity<AddLobbyResponse> {
        val response = lobbyService.addLobby(addLobbyRequest.username)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{roomId}/players")
    fun getPlayers(@PathVariable roomId: UUID): GetPlayersResponse {
        return lobbyService.getPlayers(roomId)
    }

    @PostMapping("/{roomId}")
    fun join(@PathVariable roomId: UUID, @RequestBody joinLobbyRequest: JoinLobbyRequest): ResponseEntity<JoinLobbyResponse> {
        val response = lobbyService.joinLobby(roomId, joinLobbyRequest)
        return ResponseEntity.ok(response)
    }
}
