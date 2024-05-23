package com.game.catbackend.domain.entities

import com.game.catbackend.domain.enums.Status
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    var status: Status = Status.PENDING,

    @Column(name = "room_id", nullable = false, columnDefinition = "BINARY(16)")
    var roomId: UUID = UUID.randomUUID(),
)