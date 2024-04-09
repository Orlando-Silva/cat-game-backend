package com.game.catbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatBackendApplication

fun main(args: Array<String>) {
	runApplication<CatBackendApplication>(*args)
}
