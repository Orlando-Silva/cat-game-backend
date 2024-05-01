package com.game.catbackend.data

import com.game.catbackend.domain.entities.Player
import com.github.javafaker.Faker
import java.text.SimpleDateFormat
import java.util.*

class PlayerBuilder {

    private var player: Player = Player()
    private var faker: Faker = Faker()
    private var sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")

    init {
        println(player.id)
        player.createdAt = sdf.parse(sdf.format(faker.date().birthday()))
        player.lobbyId = faker.number().randomNumber()
        player.username = faker.pokemon().name()

    }


    fun withId(id: Long): PlayerBuilder {
        this.player.id = id
        return this
    }

    fun withCreatedAt(createdAt: Date): PlayerBuilder {
        this.player.createdAt = createdAt
        return this
    }

    fun withUsername(username: String): PlayerBuilder {
        this.player.username = username
        return this
    }

    fun withLobbyId(lobbyId: Long): PlayerBuilder {
        this.player.lobbyId = lobbyId
        return this
    }

    fun build(): Player {
        return this.player
    }
}