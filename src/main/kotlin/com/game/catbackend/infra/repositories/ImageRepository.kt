package com.game.catbackend.infra.repositories

import com.game.catbackend.domain.entities.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ImageRepository : JpaRepository<Image, Long> {
    fun findByActiveTrue(): List<Image>

    @Query(
        value = "SELECT * FROM image ORDER BY RANDOM() LIMIT 3",
        nativeQuery = true)
    fun findAllThreeRandom(): List<Image>;
}