package com.example.peliculas.adapters.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.peliculas.databinding.NowPlayingBinding
import com.example.peliculas.data.model.main.Result
import com.example.peliculas.ui.views.DetalleActivity

class MovieAdapterNow(val context: Context) : RecyclerView.Adapter<MovieAdapterNow.ViewHolder>(){

    private var movieNowList = ArrayList<Result>()


    fun setMovieNowList(movieNowList: List<Result>){
        this.movieNowList = movieNowList as ArrayList<Result>
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(
                NowPlayingBinding.inflate(
                    LayoutInflater.from(parent.context)
                )
               )
    }

    override fun getItemCount(): Int {
      return movieNowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieNowList[position].poster_path)
            .into(holder.binding.movieNowImage)
        holder.binding.movieNowName.text = movieNowList[position].title
        holder.binding.voteAverageNow.text = movieNowList[position].vote_average.toString()
        holder.binding.idNow.text = movieNowList[position].id.toString()

        val nombre = holder.binding.movieNowName.text
        val id = holder.binding.idNow.text

        holder.binding.movieNowImage.setOnClickListener(View.OnClickListener {
           // Log.d("TAG_ADAPTER",id.toString())
            val intent = Intent(context, DetalleActivity::class.java)
                intent.putExtra("id",id)
           context.startActivity(intent)
        })
    }

    class ViewHolder(val binding: NowPlayingBinding): RecyclerView.ViewHolder(binding.root) {}
}