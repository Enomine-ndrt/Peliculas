package com.example.peliculas.adapters.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculas.databinding.MovieLayoutBinding
import com.example.peliculas.data.model.main.Result
import com.example.peliculas.ui.views.DetalleActivity

class MovieAdapter(val context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    private var movieList = ArrayList<Result>()

    fun setMovieList(movieList : List<Result>){
        this.movieList = movieList as ArrayList<Result>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding : MovieLayoutBinding): RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieLayoutBinding.inflate(
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
            .into(holder.binding.movieImage)
                holder.binding.movieName.text = movieList[position].title
                holder.binding.voteAverage.text = movieList[position].vote_average.toString()
                holder.binding.idMovie.text = movieList[position].id.toString()

        val nombre = holder.binding.movieName.text
        val id = holder.binding.idMovie.text

        holder.binding.movieImage.setOnClickListener(View.OnClickListener {

            val intent = Intent(context, DetalleActivity::class.java)
            intent.putExtra("id",id)
            context.startActivity(intent)
        })
     }
}