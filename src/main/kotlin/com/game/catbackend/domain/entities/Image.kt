package com.game.catbackend.domain.entities

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="image")
class Image(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    val createdAt: Date = Date(),

    @Column(name="is_active")
    var isActive: Boolean = false,

    @Column(length = 200, nullable = false)
    var source: String = ""
)