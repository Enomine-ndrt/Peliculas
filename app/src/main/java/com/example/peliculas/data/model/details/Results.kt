package com.example.peliculas.data.model.details

data class Results(
    val backdrop_path: String,
    val resultss: List<Detail>,
    val poster_path: String,
    val title: String,
    val vote_count: Int,
    val vote_average: Double,
    val overview: String,
)