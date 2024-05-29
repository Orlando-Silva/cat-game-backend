package com.game.catbackend.api.dto.request

import com.game.catbackend.domain.entities.Phrase

data class PhraseRequest(
    val text: String
)

fun PhraseRequest.toPhrase() = Phrase(
    text = text
)