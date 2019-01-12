package com.lelloman.languagedetector.demo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PercentBarView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var percent: Float = 0.5f
        set(value) {
            field = value
            postInvalidate()
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = 0xFF000000.toInt()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            val right = canvas.width * percent
            canvas.drawRect(0f, 0f, right, canvas.height.toFloat(), paint)
        }
    }
}