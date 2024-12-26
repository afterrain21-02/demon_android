package com.example.lab9

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lab9.data.Movie
import com.example.lab9.data.MovieDatabase
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MovieRepository
    val allMovies: LiveData<List<Movie>>

    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao() // Получаем экземпляр MovieDao
        repository = MovieRepository(movieDao) // Инициализируем репозиторий с MovieDao
        allMovies = repository.allMovies // Получаем все фильмы из репозитория
    }

    fun insert(movie: Movie) {
        viewModelScope.launch {
            repository.insert(movie) // Вставляем фильм в репозиторий
        }
    }

    fun deleteSelectedMovies(movieIds: List<Int>) {
        viewModelScope.launch {
            repository.deleteMovies(movieIds) // Удаляем выбранные фильмы из репозитория
        }
    }
}