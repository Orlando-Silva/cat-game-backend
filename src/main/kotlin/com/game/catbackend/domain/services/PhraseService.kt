package com.game.catbackend.domain.services

import com.game.catbackend.api.dto.PhraseDTO
import com.game.catbackend.api.dto.toPhrase
import com.game.catbackend.domain.entities.Phrase
import com.game.catbackend.domain.exceptions.CatGameInvalidAttributeException
import com.game.catbackend.infra.repositories.PhraseRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class PhraseService(val phraseRepository: PhraseRepository) {

    fun add(phraseDto: PhraseDTO): Phrase {
        val text_length = phraseDto.text.length
        val phrase = phraseDto.toPhrase()

        if (text_length == 0) throw CatGameInvalidAttributeException("The phrase text must be specified")
        if (text_length <= 200) {
            phrase.active = true
            return phraseRepository.save(phrase)
        }
        else throw CatGameInvalidAttributeException("The phrase text's length must be less than 200 characters!")

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
        return phraseRepository.findThreeRandomPhrases()
    }

}