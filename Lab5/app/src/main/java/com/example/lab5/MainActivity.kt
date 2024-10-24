package com.example.lab5

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var seekBarSale: SeekBar
    private lateinit var textViewSeekBar: TextView
    private lateinit var editTextPrice: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var ButtonOK: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarSale = findViewById(R.id.seekBarSale)
        textViewSeekBar = findViewById(R.id.textViewSeekBar)
        editTextPrice = findViewById(R.id.editTextPrice)
        radioGroup = findViewById(R.id.RadioGroup)
        ButtonOK = findViewById(R.id.buttonResult)

        seekBarSale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBarSale: SeekBar?, progress: Int, fromUser: Boolean) {
                textViewSeekBar.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBarSale: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBarSale: SeekBar?) {

            }
        })

        ButtonOK.setOnClickListener {
            calculateAndNavigate()
        }
    }

    private fun calculateAndNavigate() {
        val PriceRub = editTextPrice.text.toString().toDoubleOrNull()

        if (PriceRub == null || PriceRub <= 0) {
            Toast.makeText(this, "Пожалуйста, введите корректную цену в рублях", Toast.LENGTH_SHORT).show()
            return
        }

        val sale = seekBarSale.progress

        val selectedRadioButton = radioGroup.checkedRadioButtonId

        if (selectedRadioButton == -1) {
            Toast.makeText(this, "Пожалуйста, выберите валюту.", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedCurrency = when (selectedRadioButton) {
            R.id.radioButtonDollar -> "$"
            R.id.radioButtonEuro -> "€"
            R.id.radioButtonPound -> "£"
            else -> "₽"
        }

        val discountedPrice = ((PriceRub / 100.0) * (100.0 - sale))

        val convertedPrice = when (selectedCurrency) {
            "$" -> discountedPrice * 0.010
            "€" -> discountedPrice * 0.0096
            "£" -> discountedPrice * 0.0079
            else -> discountedPrice
        }

        // Передаем данные во вторую активность
        val next = Intent(this, MainActivity2::class.java).apply {
            putExtra("converted_price", convertedPrice)
            putExtra("currency", selectedCurrency)
        }

        startActivity(next)
    }
}