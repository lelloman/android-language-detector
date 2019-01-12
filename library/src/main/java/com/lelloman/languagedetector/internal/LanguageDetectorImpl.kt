package com.lelloman.languagedetector.internal

import com.lelloman.languagedetector.LanguageDetector
import com.lelloman.languagedetector.Languages
import org.tensorflow.lite.Interpreter
import java.nio.charset.Charset

internal class LanguageDetectorImpl(
    private val interpreter: Interpreter,
    private val stride: Int = 5
) : LanguageDetector {

    private val input = Array(1) {
        Array(20) {
            Array(256) {
                FloatArray(1)
            }
        }
    }

    private val output = Array(1) { FloatArray(Languages.size) }

    override fun detectLanguage(text: String): Array<LanguageDetector.Prediction> {

        val textBytes = text.toByteArray(Charset.forName("UTF-8"))

        val predictions = FloatArray(Languages.size)

        val nBytes = textBytes.size
        val firstStepLeftOver = Math.max(0, nBytes - 20)
        val nSteps = 1 + firstStepLeftOver / stride + Math.min(1, firstStepLeftOver % stride)

        for (i in 0 until nSteps) {
            input[0].forEach {
                it.forEach { valueArray ->
                    valueArray[0] = 0f
                }
            }
            val byteCursor = i * stride
            for (j in byteCursor until Math.min(byteCursor + 20, nBytes)) {
                val arr1 = input[0][j - byteCursor]
                arr1[textBytes[j].toUByte().toInt()][0] = 1f
            }

            interpreter.run(input, output)

            output[0].forEachIndexed { index, it ->
                predictions[index] += it
            }
        }

        predictions.forEachIndexed { index, value ->
            predictions[index] = value / nSteps
        }
        return Array(Languages.size) {
            LanguageDetector.Prediction(
                Languages[it],
                predictions[it]
            )
        }
    }

    internal class TextSanitizer {

        private val multiSpaceRegex = Regex("\\s\\s+")
        private val nonWordNonSpaceRegex = Regex("[^\\w\\s]+")
        private val leftOvers = Regex("[0-9_\n]")

        fun sanitizeText(text: String): String {
            return text
                .replace(multiSpaceRegex, " ")
                .replace(nonWordNonSpaceRegex, "")
                .replace(leftOvers, "")
                .toLowerCase()
        }
    }
}