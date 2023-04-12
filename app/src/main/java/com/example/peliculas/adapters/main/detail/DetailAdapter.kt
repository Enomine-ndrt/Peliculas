package com.example.peliculas.adapters.main.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculas.adapters.main.MovieAdapter
import com.example.peliculas.data.model.details.Detail
import com.example.peliculas.data.model.details.Results
import com.example.peliculas.data.model.main.Result
import com.example.peliculas.databinding.DetailsMovieBinding
import com.example.peliculas.databinding.MovieLayoutBinding

class DetailAdapter(val context: Context) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private var movieList = ArrayList<Detail>()

    fun setMovieList(movieList : List<Detail>){
        this.movieList = movieList as ArrayList<Detail>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : DetailsMovieBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DetailsMovieBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
            .into(holder.binding.movieImageDetail)
        holder.binding.movieNameDetails.text = movieList[position].title
        holder.binding.voteAverageDetail.text = movieList[position].vote_average.toString()
        holder.binding.overview.text = movieList[position].overview
       // holder.binding.idMovie.text = movieList[position].id.toString()
    }
}