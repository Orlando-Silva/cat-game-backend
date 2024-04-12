package com.game.catbackend.domain.services

import com.game.catbackend.CatBackendBaseTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import com.game.catbackend.api.dto.PhraseDTO
import com.game.catbackend.domain.exceptions.CatGameInvalidAttributeException

class PhraseServiceTests() : CatBackendBaseTest(){

    @Autowired
    lateinit var phraseService: PhraseService

    @Test
    fun `when add is called with text length larger than 200 characters it should return a Cat Game Invalid Attribute exception`() {
        val text = "War. War never changes. In the year 1945, my great-great grandfather, serving in the army, wondered when he'd get to go home to his wife and the son he'd never seen. He got his wish when the US ended World War II by dropping atomic bombs on Hiroshima and Nagasaki.";

        val phraseErrorException = catchThrowable {
            phraseService.add(PhraseDTO(text))
        }

        assertThat(phraseErrorException).isInstanceOf(CatGameInvalidAttributeException::class.java)
    }
}