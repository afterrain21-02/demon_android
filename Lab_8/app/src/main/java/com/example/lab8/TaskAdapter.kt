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

        holder.itemView.setOnClickListener { onDeleteClickListener(task) }

        // Implement swipe to delete functionality here.
        // This can be done using ItemTouchHelper or similar approach.
    }

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.findViewById<TextView>(R.id.task_description).text = task.description

            // Set color based on priority:
            itemView.setBackgroundColor(when (task.priority) {
                1 -> Color.GREEN // Low priority - Green color.
                2 -> Color.YELLOW // Medium priority - Yellow color.
                3 -> Color.RED // High priority - Red color.
                else -> Color.WHITE // Default color.
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