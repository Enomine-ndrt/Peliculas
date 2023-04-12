package com.example.peliculas.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.peliculas.RetrofitInstance
import com.example.peliculas.data.model.main.Movies
import com.example.peliculas.data.model.main.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()
    private var movieNowliveData = MutableLiveData<List<Result>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getPopularMovie(){
        isLoading.postValue(true)
        RetrofitInstance.api.getPopularMovies("696e7c63b6bf3356dbc9d2572a148ce1").enqueue(object : Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if(response.body() != null){
                    movieLiveData.value = response.body()!!.results
                    isLoading.postValue(false)
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
               Log.d("TAG",t.message.toString())
            }
        })
    }

    fun getNowMovie() {
        isLoading.postValue(true)
        RetrofitInstance.api.getNowPlayingMovies("696e7c63b6bf3356dbc9d2572a148ce1").enqueue(object :Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
               if(response.body() != null){
                   movieNowliveData.value = response.body()!!.results
                   isLoading.postValue(false)
               }else{
                   return
               }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
               Log.d("TAG_NOW",t.message.toString())
            }

        })
    }

    fun observeMovieliveData() : LiveData<List<Result>>{
        return movieLiveData
    }

    fun obserMovieNowLiveData() : LiveData<List<Result>>{
        return movieNowliveData
    }
}