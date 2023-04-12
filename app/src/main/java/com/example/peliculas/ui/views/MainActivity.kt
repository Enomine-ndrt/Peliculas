package com.example.peliculas.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.peliculas.ui.viewModel.MovieViewModel
import com.example.peliculas.adapters.main.MovieAdapter
import com.example.peliculas.adapters.main.MovieAdapterNow
import com.example.peliculas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    private lateinit var movieAdapterNow: MovieAdapterNow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        prepareRecyclerViewMovieNow()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovie()
        viewModel.getNowMovie()
        viewModel.observeMovieliveData().observe(this, Observer{movieList->
            movieAdapter.setMovieList(movieList)
        })
        viewModel.obserMovieNowLiveData().observe(this,Observer{movieNow ->
            movieAdapterNow.setMovieNowList(movieNow)
        })

        viewModel.isLoading.observe(this,Observer{
            binding.progress.isVisible = it
        })

    }

    private fun prepareRecyclerView() {
       movieAdapter = MovieAdapter(this)
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,1)
            adapter = movieAdapter
        }
    }

    private fun  prepareRecyclerViewMovieNow(){
        movieAdapterNow = MovieAdapterNow(this)
        binding.rvMoviesCartelera.apply {
            layoutManager = GridLayoutManager(applicationContext,1)
            adapter = movieAdapterNow
        }
    }
}