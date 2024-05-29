package com.game.catbackend.data


import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.enums.LobbyStatus
import com.github.javafaker.Faker
import java.text.SimpleDateFormat
import java.util.*

class LobbyBuilder {

    private var sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    private var faker: Faker = Faker()
    private val lobby: Lobby = Lobby()


    init {
        lobby.status = LobbyStatus.PENDING
        lobby.createdAt = sdf.parse(sdf.format(faker.date().birthday()))
        lobby.roomId = UUID.randomUUID()
    }

    fun withId(id: Long): LobbyBuilder {
        this.lobby.id = id
        return this;
    }

    fun withCreatedAt(createdAt: Date): LobbyBuilder {
        this.lobby.createdAt = createdAt;
        return this;
    }
    fun withStatus(lobbyStatus: LobbyStatus): LobbyBuilder {
        this.lobby.status = lobbyStatus
        return this
    }

    fun withRoomId(roomId: UUID): LobbyBuilder {
        this.lobby.roomId = roomId
        return this
    }

    fun build(): Lobby {
        return this.lobby
    }
}