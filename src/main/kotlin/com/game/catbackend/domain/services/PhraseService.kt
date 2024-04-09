package com.game.catbackend.domain.services

import com.game.catbackend.api.dto.PhraseDTO
import com.game.catbackend.api.dto.toPhrase
import com.game.catbackend.domain.entities.Phrase
import com.game.catbackend.infra.repositories.PhraseRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PhraseService(val phraseRepository: PhraseRepository) {

    fun add(phraseDto: PhraseDTO): Phrase {
        val phrase = phraseDto.toPhrase()
        phrase.isActive = true

        return phraseRepository.save(phrase)
    }

    fun get(id: Long): Optional<Phrase> {
        return phraseRepository.findById(id)
    }

    fun getAll(): List<Phrase> {
        return phraseRepository.findAll()
    }

}