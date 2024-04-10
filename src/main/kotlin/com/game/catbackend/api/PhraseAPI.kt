package com.game.catbackend.api

import com.game.catbackend.api.dto.PhraseDTO
import com.game.catbackend.domain.entities.Phrase
import com.game.catbackend.domain.services.PhraseService
import org.springframework.web.bind.annotation.*
import java.util.Optional


@RestController
@RequestMapping("/phrase")
class PhraseAPI(val phraseService: PhraseService) {

    @PostMapping()
    fun add(@RequestBody phraseDTO: PhraseDTO): Phrase {
        return phraseService.add(phraseDTO)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Optional<Phrase> {
        return phraseService.get(id)
    }

    @GetMapping()
    fun getAll(): List<Phrase> {
        return phraseService.getAll()
    }

    @GetMapping("/random")
    fun getLastThreePhrases(): List<Phrase> {
        return phraseService.takeLastThree()
    }


}