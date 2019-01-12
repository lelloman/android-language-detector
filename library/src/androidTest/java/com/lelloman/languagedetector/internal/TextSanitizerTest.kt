package com.lelloman.languagedetector.internal

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TextSanitizerTest {

    private val tested = LanguageDetectorImpl.TextSanitizer()

    @Test
    fun removesDuplicateSpaces() {
        assertSanitation("too    many spaces     ", "too many spaces ")
    }

    @Test
    fun convertsToLowerCase() {
        assertSanitation("AÄä", "aää")
    }

    @Test
    fun removesNonWordNonWhiteSpaceCharacters() {
        assertSanitation("\nabc/.,<>?|\"'\\;!@#$%^&*()_+=-0123456789", "abc")
    }

    private fun assertSanitation(original: String, expected: String) {
        val sanitized = tested.sanitizeText(original)

        assertThat(sanitized).isEqualTo(expected)
    }
}