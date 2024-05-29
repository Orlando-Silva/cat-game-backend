package com.game.catbackend.infra.repositories

import com.game.catbackend.domain.entities.Image
import com.game.catbackend.domain.entities.Phrase
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PhraseRepository : JpaRepository<Phrase, Long> {
    fun findByActiveTrue(): List<Phrase>

    @Query(
        value = "SELECT * FROM phrase ORDER BY RANDOM() LIMIT 3",
        nativeQuery = true)
    fun findAllThreeRandom(): List<Phrase>
}