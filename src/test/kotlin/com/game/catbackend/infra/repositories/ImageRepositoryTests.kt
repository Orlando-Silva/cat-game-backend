package com.game.catbackend.infra.repositories

import com.game.catbackend.CatBackendBaseTest
import com.game.catbackend.data.ImageBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ImageRepositoryTests : CatBackendBaseTest() {

    @Autowired
    lateinit var imageRepository: ImageRepository

    @BeforeEach
    fun cleanDatabase() {
        imageRepository.deleteAll()
    }

    @Test
    fun `when findByActiveTrue is called with no active images on database it should return empty list`() {
        imageRepository.save(
            ImageBuilder()
                .withActive(false)
                .build()
        )

        val result = imageRepository.findByActiveTrue();

        assertThat(result).isEmpty()
    }


    @Test
    fun `when findByActiveTrue is called with active images on database it should return active images list`() {
        val image = ImageBuilder()
            .withActive(true)
            .build()
        imageRepository.save(image)

        val result = imageRepository.findByActiveTrue()

        assertThat(result).hasSize(1)
    }
}