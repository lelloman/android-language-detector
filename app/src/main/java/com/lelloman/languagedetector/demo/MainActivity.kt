package com.lelloman.languagedetector.demo

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.EditText
import android.widget.TextView
import com.lelloman.languagedetector.LanguageDetector
import com.lelloman.languagedetector.LanguageDetectorFactory
import com.lelloman.languagedetector.Languages

class MainActivity : AppCompatActivity() {

    private val detector by lazy { LanguageDetectorFactory.make(this) }
    private val clipboardManager by lazy { getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
    private val detectButton by lazy { findViewById<View>(R.id.detectButton) }
    private val clearButton by lazy { findViewById<View>(R.id.clearButton) }
    private val pasteButton by lazy { findViewById<View>(R.id.pasteButton) }
    private val editText by lazy { findViewById<EditText>(R.id.editText) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private val clipboardText get() = clipboardManager.primaryClip?.getItemAt(0)?.text ?: ""

    private var predictions = Languages
        .map { LanguageDetector.Prediction(it, 0f) }
        .toTypedArray()

    private val density get() = resources.displayMetrics.density

    private val adapter = object : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(container: ViewGroup, viewType: Int) = LayoutInflater
            .from(this@MainActivity)
            .inflate(R.layout.list_item_prediction, container, false)
            .let(::ViewHolder)

        override fun getItemCount() = predictions.size

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val prediction = predictions[position]
            viewHolder.percentBarView.percent = prediction.percent
            viewHolder.textView.text = prediction.language.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        detectButton.setOnClickListener {
            val text = editText.text.toString()
            predictions = detector.detectLanguage(text)
            adapter.notifyItemRangeChanged(0, predictions.size)
        }
        clearButton.setOnClickListener { editText.setText("") }
        pasteButton.setOnClickListener { editText.setText(clipboardText) }

        val gridLayoutManager = GridLayoutManager(this, 1)
        val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            val spanCount = (recyclerView.width / density) / 160f
            gridLayoutManager.spanCount = spanCount.toInt()
        }
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
    }

    private class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val textView: TextView = rootView.findViewById(R.id.textView)
        val percentBarView: PercentBarView = rootView.findViewById(R.id.percentBarView)
    }
}
