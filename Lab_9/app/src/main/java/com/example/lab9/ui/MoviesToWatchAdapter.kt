package com.example.lab9.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.lab9.databinding.ItemMovieBinding
import com.example.lab9.extenstion.fetchImage
import com.example.lab9.model.Movie


class MoviesToWatchAdapter(private val moviesList: List<Movie>) : RecyclerView.Adapter<MoviesToWatchAdapter.MovieViewHolder>() {

    private val selectedMovies = mutableSetOf<Movie>()

    class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, isSelected: Boolean, onSelectChangeListener: (Boolean) -> Unit) {
            binding.textViewTitle.text = movie.title
            binding.textViewYear.text = movie.year
            binding.textViewType.isVisible = false
            binding.imageViewPoster.fetchImage(movie.poster)
            binding.checkboxSelect.isChecked = isSelected

            binding.checkboxSelect.setOnCheckedChangeListener { _, isChecked ->
                onSelectChangeListener(isChecked)
            }

            itemView.setOnClickListener {
                binding.checkboxSelect.isChecked = !binding.checkboxSelect.isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position], selectedMovies.contains(moviesList[position])) { isSelected ->
            if (isSelected) {
                selectedMovies.add(moviesList[position])
            } else {
                selectedMovies.remove(moviesList[position])
            }
        }
    }

    override fun getItemCount(): Int = moviesList.size

    fun getSelectedMovies(): List<Movie> {
        return selectedMovies.toList()
    }
}
