package com.example.lab5

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textViewResult = findViewById(R.id.textViewFinalPrice)

        val convertedPrice = intent.getDoubleExtra("converted_price", 0.0)
        val currency = intent.getStringExtra("currency") ?: "Рубли"

        textViewResult.text = String.format("%.2f %s", convertedPrice, currency)
    }
}