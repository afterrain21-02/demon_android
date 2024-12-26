package com.example.lab9.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("your_api_endpoint") // Замените на ваш реальный эндпоинт
    suspend fun getMovies(@Query("query") queryString: String): Response<MovieResponse>
}
