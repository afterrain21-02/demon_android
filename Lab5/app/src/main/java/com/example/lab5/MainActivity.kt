package com.example.lab5

import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var seekBarSale: SeekBar
    private lateinit var textViewSeekBar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarSale = findViewById(R.id.seekBarSale)
        textViewSeekBar = findViewById(R.id.textViewSeekBar)

        seekBarSale.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBarSale: SeekBar?, progress: Int, fromUser: Boolean) {
                textViewSeekBar.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBarSale: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBarSale: SeekBar?) {

            }
        })


    }
}