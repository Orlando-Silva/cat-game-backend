package com.game.catbackend.data

import com.game.catbackend.domain.entities.Image
import com.github.javafaker.Faker
import java.util.*

class ImageBuilder {

    private var faker: Faker = Faker()
    private val image: Image = Image()

    init {
        image.id = faker.number().randomNumber()
        image.active = faker.bool().bool()
        image.source = faker.name().title()
        image.createdAt = faker.date().birthday()

    }

    fun withId(id: Long): ImageBuilder {
        this.image.id = id
        return this;
    }

    fun withCreatedAt(createdAt: Date): ImageBuilder {
        this.image.createdAt = createdAt;
        return this;
    }

    fun withActive(active: Boolean): ImageBuilder {
        this.image.active = active;
        return this;
    }

    fun withSource(source: String): ImageBuilder {
        this.image.source = source;
        return this;
    }

    fun build(): Image {
        return this.image
    }

}