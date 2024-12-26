package com.example.lab9.api


import com.example.lab9.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/")
    suspend fun searchMovies(
        @Query("s") search: String,
        @Query("y") year: String? = null
    ): ApiResponse

    @GET("/")
    suspend fun getMovie(
        @Query("t") search: String,
        @Query("y") year: String? = null
    ): ApiResponse
}