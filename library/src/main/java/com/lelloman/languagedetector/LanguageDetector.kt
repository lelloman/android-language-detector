package com.lelloman.languagedetector

interface LanguageDetector {

    fun detectLanguage(text: String): Array<Prediction>

    data class Prediction(val language: Language, val percent: Float)
}