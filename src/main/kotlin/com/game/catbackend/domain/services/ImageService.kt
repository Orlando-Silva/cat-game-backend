package com.game.catbackend.domain.services

import com.game.catbackend.api.dto.ImageDTO
import com.game.catbackend.api.dto.toImage
import com.game.catbackend.domain.entities.Image
import com.game.catbackend.infra.repositories.ImageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImageService(val imageRepository: ImageRepository) {

    fun add(imageDto: ImageDTO): Image {
        val image = imageDto.toImage()
        image.isActive = true

        return imageRepository.save(image)
    }

    fun get(id: Long): Optional<Image> {
        return imageRepository.findById(id)
    }

    fun getAll(): List<Image> {
        return imageRepository.findAll()
    }

}