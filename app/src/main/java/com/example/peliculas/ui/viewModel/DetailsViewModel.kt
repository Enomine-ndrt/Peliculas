package com.example.peliculas.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.peliculas.RetrofitInstance
import com.example.peliculas.data.model.details.Detail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//import com.example.peliculas.data.model.main.Result

class DetailsViewModel: ViewModel() {


    private var movieLiveData = MutableLiveData<List<Detail>>()


    fun getMovieDetails( id : Int){
        RetrofitInstance.api.getDetailsMovies(id,"696e7c63b6bf3356dbc9d2572a148ce1").enqueue(object : Callback<Detail>{

            override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                if(response.body() != null){
                    movieLiveData.value = mutableListOf(response.body()!!)
                }else{
                    return
                }
            }
            override fun onFailure(call: Call<Detail>, t: Throwable) {
                Log.d("TAG_Details",t.message.toString())
            }
        })
    }

    fun obserMovieDetailLiveData(): LiveData<List<Detail>> {
        return movieLiveData
    }
}

