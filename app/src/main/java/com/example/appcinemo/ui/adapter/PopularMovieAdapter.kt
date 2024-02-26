package com.example.cinemo.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appcinemo.R
import com.example.appcinemo.data.utils.Util
import com.example.appcinemo.ui.activity.MovieDetailsActivity
import com.example.cinemo.data.model.movie.Result



class PopularMovieAdapter(val ctx: Context, val movies: List<Result>) :
    RecyclerView.Adapter<PopularMovieAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(ctx)
            .inflate(R.layout.single_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        val movie: Result = movies[position]

        Glide
            .with(ctx)
            .load(Util.posterUrlMake(movie.posterPath))
            .into(viewHolder.poster)

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(ctx, MovieDetailsActivity::class.java)
            val movieId:String = movie.id.toString()
            intent.putExtra("MovieIdPass",movieId)
            ctx.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster = itemView.findViewById<ImageView>(R.id.imageView_single_movie)

    }
}