package com.example.lab9

import androidx.lifecycle.LiveData
import com.example.lab9.data.Movie
import com.example.lab9.data.MovieDao

class MovieRepository(private val movieDao: MovieDao) {
    val allMovies: LiveData<List<Movie>> = movieDao.getAllMovies()

    suspend fun insert(movie: Movie) {
        movieDao.insert(movie)
    }

    suspend fun deleteMovies(movieIds: List<Int>) {
        movieDao.deleteMovies(movieIds)
    }
}
