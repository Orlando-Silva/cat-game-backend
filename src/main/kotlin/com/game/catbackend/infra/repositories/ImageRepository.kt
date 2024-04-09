package com.game.catbackend.infra.repositories

import com.game.catbackend.domain.entities.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long> {

}