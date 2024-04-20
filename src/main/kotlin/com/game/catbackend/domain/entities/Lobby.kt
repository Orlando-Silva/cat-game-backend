package com.game.catbackend.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="lobby")
class Lobby(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    var createdAt: Date = Date(),

    @Column(name="status", length = 50)
    var status: String = "",

    @Column(name = "room_id")
    var roomId: UUID? = UUID.randomUUID(),

    @Column(name="host_id", nullable = false)
    var hostId: Long = 0
)