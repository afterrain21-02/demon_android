package com.example.lab8

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lab8.data.Task

class TaskAdapter(private val onDeleteClickListener: (Task) -> Unit) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = getItem(position)
        holder.bind(task)

        // Устанавливаем обработчик клика для удаления задачи (или другого действия)
        holder.itemView.setOnClickListener { onDeleteClickListener(task) }
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.findViewById<TextView>(R.id.task_description).text = task.description

            // Устанавливаем цвет фона в зависимости от приоритета:
            itemView.setBackgroundColor(when (task.priority) {
                1 -> Color.GREEN // Низкий приоритет - зеленый цвет.
                2 -> Color.YELLOW // Средний приоритет - желтый цвет.
                3 -> Color.RED // Высокий приоритет - красный цвет.
                else -> Color.WHITE // Цвет по умолчанию.
            })
        }
    }
}

object TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
        oldItem == newItem
}
