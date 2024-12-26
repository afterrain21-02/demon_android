package com.example.lab9.response

import com.example.lab9.model.Movie


sealed class ApiResult {
    data class Success(val movies: List<Movie?>) : ApiResult()
    data class Error(val message: String) : ApiResult()
}
