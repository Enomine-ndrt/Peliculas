package com.example.peliculas.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.peliculas.adapters.main.MovieAdapter
import com.example.peliculas.databinding.ActivityDetalleBinding
import com.example.peliculas.ui.viewModel.DetailsViewModel
import com.example.peliculas.ui.viewModel.MovieViewModel
import com.example.peliculas.adapters.main.detail.DetailAdapter

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding
    private lateinit var viewModel: DetailsViewModel
    private lateinit var detailsAdapter : DetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val id = bundle?.getString("id");
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        viewModel.getMovieDetails(Integer.parseInt(id))

       //
       viewModel.obserMovieDetailLiveData().observe(this,Observer{list ->
           detailsAdapter.setMovieList(list)
        })
    }

    private fun prepareRecyclerView() {
        detailsAdapter = DetailAdapter(this)
        binding.detailsMoviesNow.apply {
            layoutManager = GridLayoutManager(applicationContext,1)
            adapter = detailsAdapter
        }
    }
}