package com.game.catbackend.infra.repositories

import com.game.catbackend.domain.entities.Phrase
import org.springframework.data.jpa.repository.JpaRepository

interface PhraseRepository : JpaRepository<Phrase, Long> {
    fun findByActiveTrue(): List<Phrase>
}