package com.game.catbackend.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="image")
class Image(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    var createdAt: Date = Date(),

    @Column(name="active")
    var active: Boolean = false,

    @Column(length = 200, nullable = false)
    var source: String = ""
)