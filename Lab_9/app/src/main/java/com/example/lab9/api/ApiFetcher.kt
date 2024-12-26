package com.example.lab9.api

import com.example.lab9.repository.MovieRepository
import com.example.lab9.response.ApiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ApiFetcher(private val movieRepository: MovieRepository) {

    fun fetchMoviesList(searchQuery: String, year: String? = null, callback: (ApiResult) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = movieRepository.searchMovies(searchQuery, year)
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }

    fun fetchMovie(title: String, year: String?, callback: (ApiResult) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = movieRepository.getMovie(title, year)
            withContext(Dispatchers.Main) {
                callback(result)
            }
        }
    }
}
