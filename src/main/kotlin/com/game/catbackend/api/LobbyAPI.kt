package com.game.catbackend.api

import com.game.catbackend.api.dto.response.AddLobbyDTO
import com.game.catbackend.api.dto.LobbyDTO
import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.services.LobbyService
import jakarta.validation.Valid
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
}