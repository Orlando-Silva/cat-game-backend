package com.game.catbackend.domain.services

import com.game.catbackend.api.dto.request.ImageRequest
import com.game.catbackend.api.dto.request.toImage
import com.game.catbackend.domain.entities.Image
import com.game.catbackend.domain.exceptions.CatGameInvalidAttributeException
import com.game.catbackend.infra.repositories.ImageRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImageService(val imageRepository: ImageRepository) {

    fun add(imageRequest: ImageRequest): Image {
        val image = imageRequest.toImage()
        image.active = true

        if (image.source.length > 200) {
            throw CatGameInvalidAttributeException("The length of image source needs to be less than 200 characters!")
        }

        return imageRepository.save(image)
    }

    fun get(id: Long): Optional<Image> {
        return imageRepository.findById(id)
    }

    fun getAll(): List<Image> {
        return imageRepository.findByActiveTrue()
    }

    fun getRandom(): List<Image> {
        return imageRepository.findAllThreeRandom()
    }

}