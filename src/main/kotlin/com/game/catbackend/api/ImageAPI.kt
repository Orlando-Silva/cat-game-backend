package com.game.catbackend.api

import com.game.catbackend.api.dto.request.ImageRequest
import com.game.catbackend.domain.entities.Image
import com.game.catbackend.domain.services.ImageService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("/image")
class ImageAPI(val imageService: ImageService) {

    @PostMapping()
    fun add(@RequestBody @Valid imageRequest: ImageRequest): ResponseEntity<Image> {
        return ResponseEntity.ok(imageService.add(imageRequest))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Optional<Image>> {
        return ResponseEntity.ok(imageService.get(id))
    }

    @GetMapping()
    fun getAll(): ResponseEntity<List<Image>> {
        return ResponseEntity.ok(imageService.getAll())
    }

    @GetMapping("/random")
    fun getRandom(): ResponseEntity<List<Image>> {
        return ResponseEntity.ok(imageService.getRandom())
    }

}