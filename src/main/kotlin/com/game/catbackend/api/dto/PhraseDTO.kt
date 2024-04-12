package com.game.catbackend.api.dto

import com.game.catbackend.domain.entities.Phrase
import jakarta.validation.constraints.Size

data class PhraseDTO(
    @Size(max = 200, message = "The length of phrase text needs to be less than 200 characters!")
    val text: String
)

fun PhraseDTO.toPhrase() = Phrase(
    text = text
)