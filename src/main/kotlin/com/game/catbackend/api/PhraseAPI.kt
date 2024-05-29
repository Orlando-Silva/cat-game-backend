package com.game.catbackend.api

import com.game.catbackend.api.dto.request.PhraseRequest
import com.game.catbackend.domain.entities.Phrase
import com.game.catbackend.domain.services.PhraseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional


@RestController
@RequestMapping("/phrase")
class PhraseAPI(val phraseService: PhraseService) {

    @PostMapping()
    fun add(@RequestBody phraseRequest: PhraseRequest): ResponseEntity<Phrase> {
        return ResponseEntity.ok(phraseService.add(phraseRequest))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Optional<Phrase>> {
        return ResponseEntity.ok(phraseService.get(id))
    }

    @GetMapping()
    fun getAll(): ResponseEntity<List<Phrase>> {
        return ResponseEntity.ok(phraseService.getAll())
    }

    @GetMapping("/random")
    fun getRandom(): ResponseEntity<List<Phrase>> {
        return ResponseEntity.ok(phraseService.getThreeRandomPhrases())
    }


}