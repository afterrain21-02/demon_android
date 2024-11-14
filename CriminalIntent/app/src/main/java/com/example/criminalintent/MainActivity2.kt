package com.example.criminalintent

import AppDatabase
import Crime
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ClipData
import android.content.Intent
import android.media.RouteListingPreference
import android.media.RouteListingPreference.Item
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity2 : AppCompatActivity() {

    private lateinit var buttonSave: Button
    private lateinit var editTextDate: EditText
    private lateinit var imageViewAddPhoto: ImageView
    private lateinit var imageViewShowPhoto: ImageView
    private lateinit var editTextName: EditText
    private lateinit var checkBoxSolved: CheckBox
    private var selectedPhotoUri: Uri? = null

    private val pickImageContract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedPhotoUri = it
            imageViewShowPhoto.setImageURI(it)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        buttonSave = findViewById(R.id.buttonSave)
        editTextDate = findViewById(R.id.editTextDate)
        imageViewAddPhoto = findViewById(R.id.imageViewAddPhoto)
        imageViewShowPhoto = findViewById(R.id.imageViewShowPhoto)
        editTextName = findViewById(R.id.editTextName)
        checkBoxSolved = findViewById(R.id.checkBoxSolved)

        editTextDate.setOnClickListener {
            showDatePicker()
        }

        imageViewAddPhoto.setOnClickListener {
            onAddPhotoClick(it)
        }

        val db = AppDatabase.getDatabase(this)

            buttonSave.setOnClickListener {
            val crime = Crime(
                title = editTextName.text.toString(),
                date = convertDateToMillis(editTextDate.text.toString()),
                isSolved = checkBoxSolved.isChecked,
                photoPath = selectedPhotoUri?.toString()
            )

            Thread{
                db.crimeDao().insert(crime)
            }
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

    private fun convertDateToMillis(dateString: String): Long {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return try {
            val date = format.parse(dateString) // Преобразуем строку в Date
            date?.time ?: 0 // Возвращаем миллисекунды или 0, если дата null
        } catch (e: Exception) {
            0 // Обрабатываем исключение, возвращаем 0, если произошла ошибка
        }
    }

}