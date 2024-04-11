package com.game.catbackend.api.dto

import com.game.catbackend.domain.entities.Image
import jakarta.validation.constraints.Size

class ImageDTO(
    @Size(max = 200, message = "The length of image source needs to be less than 200 characters!")
    val source: String = ""
)

fun ImageDTO.toImage() = Image(
    source = source
)