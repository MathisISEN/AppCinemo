package com.example.cinemo.data.model.movie.movieCredit


import com.example.appcinemo.data.model.movie.movieCredit.Cast
import com.google.gson.annotations.SerializedName

data class MovieCredit(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)