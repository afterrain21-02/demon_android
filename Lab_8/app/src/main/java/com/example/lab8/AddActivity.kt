package com.example.lab8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.lab8.data.AppDatabase
import com.example.lab8.data.Task

class AddActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        db = AppDatabase.getDatabase(applicationContext)

        findViewById<Button>(R.id.button_add).setOnClickListener {
            addTask()
        }
    }

    private fun addTask() {
        val description = findViewById<EditText>(R.id.edit_text_description).text.toString()

        val priority = when (findViewById<RadioGroup>(R.id.radio_group_priority).checkedRadioButtonId) {
            R.id.radio_low -> 1 // Low priority
            R.id.radio_medium -> 2 // Medium priority
            R.id.radio_high -> 3 // High priority
            else -> return // Exit if no priority selected.
        }

        val newTask = Task(description = description, priority = priority)

        lifecycleScope.launch {
            db.taskDao().insert(newTask)
            finish() // Close the activity after adding the task.
        }
    }
}