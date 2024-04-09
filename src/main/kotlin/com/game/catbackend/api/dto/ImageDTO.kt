package com.game.catbackend.api.dto

import com.game.catbackend.domain.entities.Image

data class ImageDTO(
    val source: String
)

fun ImageDTO.toImage() = Image(
    source = source
)