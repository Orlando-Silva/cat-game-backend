package com.game.catbackend.data


import com.game.catbackend.domain.entities.Lobby
import com.game.catbackend.domain.enums.Status
import com.github.javafaker.Faker
import java.text.SimpleDateFormat
import java.util.*

class LobbyBuilder {

    private var sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
    private var faker: Faker = Faker()
    private val lobby: Lobby = Lobby()


    init {
        lobby.status = Status.PENDING
        lobby.createdAt = sdf.parse(sdf.format(faker.date().birthday()))
        lobby.roomId = UUID.randomUUID()
        lobby.hostId = faker.number().randomNumber()

    }

    fun withId(id: Long): LobbyBuilder {
        this.lobby.id = id
        return this;
    }

    fun withCreatedAt(createdAt: Date): LobbyBuilder {
        this.lobby.createdAt = createdAt;
        return this;
    }
    fun withStatus(status: Status): LobbyBuilder {
        this.lobby.status = status
        return this
    }

    fun withRoomId(roomId: UUID): LobbyBuilder {
        this.lobby.roomId = roomId
        return this
    }

    fun withHostId(hostId: Long): LobbyBuilder {
        this.lobby.hostId = hostId
        return this
    }

    fun build(): Lobby {
        return this.lobby
    }
}