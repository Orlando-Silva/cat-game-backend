package com.game.catbackend.api

import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.services.LobbyService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/lobby")
class LobbyAPI(val lobbyService: LobbyService) {

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Optional<Lobby> {
        return lobbyService.get(id)
    }
}