package com.example.lab9

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.room.InvalidationTracker

class MainActivity : AppCompatActivity() {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация RecyclerView и ViewModel
        movieAdapter = MovieAdapter()
        recyclerView.adapter = movieAdapter
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        // Наблюдение за данными
        movieViewModel.allMovies.observe(this, InvalidationTracker.Observer { movies ->
            movies?.let { movieAdapter.submitList(it) }
        })

        fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }
}
