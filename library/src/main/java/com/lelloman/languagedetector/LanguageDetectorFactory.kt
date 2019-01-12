package com.lelloman.languagedetector

import android.content.Context
import com.lelloman.languagedetector.internal.LanguageDetectorImpl
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.channels.FileChannel

object LanguageDetectorFactory {

    fun make(context: Context): LanguageDetector {
        val fileDescriptor = context.assets.openFd("converted_model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        val modelFile = fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        return LanguageDetectorImpl(
            interpreter = Interpreter(modelFile),
            stride = 8
        )
    }
}