package com.game.catbackend.domain.exceptions

class CatGamePlayerAlreadyExistsException(override val message: String?): CatGameBaseException(message) {
}