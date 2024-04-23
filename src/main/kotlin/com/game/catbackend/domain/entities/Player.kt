package com.game.catbackend.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="player")
class Player (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    val createdAt: Date = Date(),

    @Column(length = 50, nullable = false)
    val username: String = "",

    @Column(name = "lobby_id", nullable = false)
    val lobbyId: Long
)