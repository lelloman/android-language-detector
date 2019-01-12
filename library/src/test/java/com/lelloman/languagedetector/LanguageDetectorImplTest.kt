package com.lelloman.languagedetector

import com.lelloman.languagedetector.internal.LanguageDetectorImpl
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.tensorflow.lite.Interpreter

class LanguageDetectorImplTest {

    private val interpreter: Interpreter = mock()

    private fun makeDetector(stride: Int) = LanguageDetectorImpl(
        interpreter = interpreter,
        stride = stride
    )

    @Test
    fun `run the interpreter once for string 10 chars long`() {
        val tested = makeDetector(1)

        tested.detectLanguage(" ".repeat(10))

        verify(interpreter).run(any(), any())
        verifyNoMoreInteractions(interpreter)
    }

    @Test
    fun `run the interpreter once for string 20 chars long`() {
        val tested = makeDetector(1)

        tested.detectLanguage(" ".repeat(20))

        verify(interpreter).run(any(), any())
        verifyNoMoreInteractions(interpreter)
    }

    @Test
    fun `run the interpreter 2 times for string 25 chars long with stride 5`() {
        val tested = makeDetector(5)

        tested.detectLanguage(" ".repeat(25))

        verify(interpreter, times(2)).run(any(), any())
        verifyNoMoreInteractions(interpreter)
    }

    @Test
    fun `run the interpreter 3 times for string 26 chars long with stride 5`() {
        val tested = makeDetector(5)

        tested.detectLanguage(" ".repeat(26))

        verify(interpreter, times(3)).run(any(), any())
        verifyNoMoreInteractions(interpreter)
    }

    @Test
    fun `run the interpreter 3 times for string 30 chars long with stride 5`() {
        val tested = makeDetector(5)

        tested.detectLanguage(" ".repeat(30))

        verify(interpreter, times(3)).run(any(), any())
        verifyNoMoreInteractions(interpreter)
    }

    @Test
    fun `run the interpreter 4 times for string 31 chars long with stride 5`() {
        val tested = makeDetector(5)

        tested.detectLanguage(" ".repeat(31))

        verify(interpreter, times(4)).run(any(), any())
        verifyNoMoreInteractions(interpreter)
    }
}