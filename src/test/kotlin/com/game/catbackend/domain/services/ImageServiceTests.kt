package com.game.catbackend.domain.services

import com.game.catbackend.CatBackendBaseTest
import com.game.catbackend.api.dto.ImageDTO
import com.game.catbackend.domain.exceptions.CatGameInvalidAttributeException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class ImageServiceTests() : CatBackendBaseTest(){

    @Autowired
    lateinit var imageService: ImageService

    @Test
     fun `when add is called with source length more than 200 characters it should return an exception`() {
        val source = "https://i.natgeofe.com/n/548467d8-c5f1-4551-9f58-6817a8d2c45e/NationalGeographic_2572187_square/NationalGeographic_2572187_square/NationalGeographic_2572187_square/NationalGeographic_2572187_square/NationalGeographic_2572187_square.jpg";

        val imageErrorException = catchThrowable {
            imageService.add(ImageDTO(source))
        }

        assertThat(imageErrorException).isInstanceOf(CatGameInvalidAttributeException::class.java)
    }
}