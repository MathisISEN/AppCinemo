package com.example.appcinemo.data.network

import com.example.cinemo.data.model.TV.TvShow
import com.example.cinemo.data.model.movie.Movie
import com.example.cinemo.data.model.movie.YoutubeTrailer.MovieTrailer
import com.example.cinemo.data.model.movie.movieCredit.MovieCredit
import com.example.cinemo.data.model.movie.movieDetails.MovieDetails
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY = "0b889eb43ff56a47b143432d52da2cfb"

interface ApiService {

    //Movies

    @GET("3/movie/upcoming?api_key=$API_KEY")
    suspend fun getUpcomingMovies(@Query("language") language: String,@Query("page") page : Int): Response<Movie>

    @GET("3/movie/popular?api_key=$API_KEY")
    suspend fun getPopularMovies(@Query("language") language: String,@Query("page") page : Int): Response<Movie>

    @GET("3/movie/top_rated?api_key=$API_KEY")
    suspend fun getTopRatedMovies(@Query("language") language: String,@Query("page") page : Int): Response<Movie>

    @GET("3/movie/{movieId}?api_key=$API_KEY")
    suspend fun getMovieDetails(@Path("movieId") movieId: String, @Query("language") language: String): Response<MovieDetails>


    // People
    @GET("3/movie/{movieId}/credits?api_key=$API_KEY")
    suspend fun getMovieCredit(@Path("movieId") movieId: String, @Query("language") language: String): Response<MovieCredit>


    // video

    @GET("3/movie/{movieId}/videos?api_key=$API_KEY")
    suspend fun getMovieTrailer(@Path("movieId") movieId: String, @Query("language") language: String): Response<MovieTrailer>


    //tv show

    @GET("3/tv/latest?api_key=$API_KEY")
    suspend fun getLatestTvShow(@Query("language") language: String,@Query("page") page : Int): Response<TvShow>

}

class RetrofitService {

    fun getRetrofitService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}