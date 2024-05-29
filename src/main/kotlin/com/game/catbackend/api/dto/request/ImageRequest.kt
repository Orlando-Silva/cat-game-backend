package com.game.catbackend.api.dto.request

import com.game.catbackend.domain.entities.Image
import jakarta.validation.constraints.Size

class ImageRequest(
    @Size(max = 200, message = "The length of image source needs to be less than 200 characters!")
    val source: String
)

fun ImageRequest.toImage() = Image(
    source = source
)