package com.game.catbackend.domain.services

import com.game.catbackend.api.dto.request.PhraseRequest
import com.game.catbackend.api.dto.request.toPhrase
import com.game.catbackend.domain.entities.Phrase
import com.game.catbackend.infra.repositories.PhraseRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PhraseService(val phraseRepository: PhraseRepository) {

    fun add(phraseRequest: PhraseRequest): Phrase {
        val phrase = phraseRequest.toPhrase()
        phrase.active = true

        return phraseRepository.save(phrase)
    }

    fun get(id: Long): Optional<Phrase> {
        return phraseRepository.findById(id)
    }

    fun getAll(): List<Phrase> {
        return phraseRepository.findByActiveTrue()
    }

    fun takeLastThree(): List<Phrase> {
        return phraseRepository.findByActiveTrue().takeLast(3)
    }

    fun getThreeRandomPhrases(): List<Phrase> {
        return phraseRepository.findAllThreeRandom()
    }

}