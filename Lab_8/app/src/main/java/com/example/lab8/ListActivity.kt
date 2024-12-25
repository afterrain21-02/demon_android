package com.example.lab8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.lab8.data.AppDatabase
import com.example.lab8.data.Task
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase // Переменная для базы данных
    private lateinit var taskAdapter: TaskAdapter // Адаптер для списка задач

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list) // Устанавливаем макет для активности

        db = AppDatabase.getDatabase(applicationContext) // Инициализация базы данных
        taskAdapter = TaskAdapter { task -> deleteTask(task) } // Инициализация адаптера с обработчиком удаления

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view) // Получаем RecyclerView из макета
        recyclerView.layoutManager = LinearLayoutManager(this) // Устанавливаем менеджер компоновки
        recyclerView.adapter = taskAdapter // Устанавливаем адаптер для RecyclerView

        // Наблюдаем за изменениями в списке задач из базы данных
        db.taskDao().getAllTasks().observe(this) { tasks ->
            taskAdapter.submitList(tasks) // Обновляем список задач в адаптере
        }

        // Обработчик нажатия на кнопку добавления задачи
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java)) // Переход к AddActivity
        }

        // Создание ItemTouchHelper для удаления задач по свайпу
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false // Не поддерживаем движение
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition // Получаем позицию элемента, который был свайпнут
                val task = taskAdapter.currentList[position] // Получаем задачу по позиции из текущего списка адаптера
                deleteTask(task) // Удаляем задачу
            }
        })

        itemTouchHelper.attachToRecyclerView(recyclerView) // Привязываем ItemTouchHelper к RecyclerView
    }

    private fun deleteTask(task: Task) {
        lifecycleScope.launch {
            db.taskDao().delete(task) // Удаление задачи из базы данных в фоновом потоке
        }
    }
}
