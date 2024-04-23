package com.game.catbackend.domain.services

import com.game.catbackend.infra.repositories.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(val playerRepository: PlayerRepository) {
}