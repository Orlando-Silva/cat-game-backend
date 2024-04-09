package com.game.catbackend.api.dto

import com.game.catbackend.domain.entities.Phrase

data class PhraseDTO(
    val text: String
)

fun PhraseDTO.toPhrase() = Phrase(
    text = text
)