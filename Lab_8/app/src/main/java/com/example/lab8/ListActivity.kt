package com.example.lab8

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.lab8.data.AppDatabase
import com.example.lab8.data.Task
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        db = AppDatabase.getDatabase(applicationContext)
        taskAdapter = TaskAdapter { task -> deleteTask(task) }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = taskAdapter

        db.taskDao().getAllTasks().observe(this) { tasks ->
            taskAdapter.submitList(tasks)
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun deleteTask(task: Task) {
        lifecycleScope.launch {
            db.taskDao().delete(task)
        }
    }
}