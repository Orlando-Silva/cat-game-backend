package com.game.catbackend.domain.entities

import jakarta.persistence.*
import java.util.*


@Entity
@Table(name="phrase")
class Phrase(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Temporal(TemporalType.DATE)
    @Column(name="created_at")
    val createdAt: Date = Date(),

    @Column(name="active")
    var active: Boolean = false,

    @Column(length = 200, nullable = false)
    var text: String = ""
)