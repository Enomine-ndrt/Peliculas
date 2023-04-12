package com.example.peliculas

import com.example.peliculas.data.model.details.Detail
import com.example.peliculas.data.model.details.Results
import com.example.peliculas.data.model.main.Movies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("top_rated?")
    fun getPopularMovies(@Query("api_key")api_key: String): Call<Movies>
    @GET("now_playing?")
    fun getNowPlayingMovies(@Query("api_key")api_key: String): Call<Movies>
    @GET("{movie_id}?")
    fun getDetailsMovies(@Path("movie_id") movie_id: Int, @Query("api_key")api_key: String): Call<Detail>
}

object RetrofitInstance {
    val api : MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }
}
