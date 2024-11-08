package com.example.criminalintent

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class MainActivity2 : AppCompatActivity() {

    private lateinit var buttonSave: Button
    private lateinit var editTextDate: EditText
    private lateinit var imageViewAddPhoto: ImageView
    private lateinit var imageViewShowPhoto: ImageView
    private var selectedPhotoUri: Uri? = null

    private val pickImageContract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedPhotoUri = it
            imageViewShowPhoto.setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        buttonSave = findViewById(R.id.buttonSave)
        editTextDate = findViewById(R.id.editTextDate)
        imageViewAddPhoto = findViewById(R.id.imageViewAddPhoto)
        imageViewShowPhoto = findViewById(R.id.imageViewShowPhoto)

        editTextDate.setOnClickListener {
            showDatePicker()
        }

        imageViewAddPhoto.setOnClickListener {
            onAddPhotoClick(it)
        }
    }

    fun onAddPhotoClick(view: android.view.View) {
        pickImageContract.launch("image/*")
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                editTextDate.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }
}