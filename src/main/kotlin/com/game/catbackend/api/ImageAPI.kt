package com.game.catbackend.api

import com.game.catbackend.api.dto.ImageDTO
import com.game.catbackend.domain.entities.Image
import com.game.catbackend.domain.services.ImageService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("/image")
class ImageAPI(val imageService: ImageService) {

    @PostMapping()
    fun add(@RequestBody @Valid imageDTO: ImageDTO ): Image {
        return imageService.add(imageDTO)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Optional<Image> {
        return imageService.get(id)
    }
    @GetMapping()
    fun getAll(): List<Image> {
        return imageService.getAll()
    }

    @GetMapping("/random")
    fun getRandom(): List<Image> {
        return imageService.getRandom()
    }

}