package com.example.lab9.response


sealed class ApiResult {
    data class Success(val movies: List<Movie?>) : ApiResult()
    data class Error(val message: String) : ApiResult()
}
