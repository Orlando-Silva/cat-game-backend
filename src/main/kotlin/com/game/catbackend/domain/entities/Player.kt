package com.game.catbackend.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="player")
class Player (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    var createdAt: Date = Date(),

    @Column(length = 50, nullable = false)
    var username: String = "",

    @Column(name = "lobby_id", nullable = false)
    var lobbyId: Long = 0
)