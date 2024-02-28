package com.example.appcinemo.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import com.example.appcinemo.R
import com.example.appcinemo.ui.activity.SeeAllMovieActivity
import com.example.cinemo.data.viewModel.MovieViewModel
import com.example.cinemo.ui.adapter.PopularMovieAdapter

@AndroidEntryPoint
class MovieFragment : Fragment() {

    lateinit var popularMovieAdapter: PopularMovieAdapter
    lateinit var topRatedMovieAdapter: PopularMovieAdapter

    lateinit var movieViewModel: MovieViewModel
    lateinit var popularMovieRecView_moviesFragment: RecyclerView
    lateinit var topRated_MovieLayout_movieFrag: LinearLayout
    lateinit var topRatedMovieRecView_moviesFragment: RecyclerView

    lateinit var popular_MovieLayout_movieFrag: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_movie, container, false)

        popularMovieRecView_moviesFragment = view.findViewById(R.id.popularMovieRecView_moviesFragment)
        popular_MovieLayout_movieFrag = view.findViewById(R.id.popular_MovieLayout_movieFrag)
        topRated_MovieLayout_movieFrag = view.findViewById(R.id.topRated_MovieLayout_movieFrag)
        topRatedMovieRecView_moviesFragment = view.findViewById(R.id.topRatedMovieRecView_moviesFragment)
        val popular_MovieSeeAll_movieFrag = view.findViewById<TextView>(R.id.popular_MovieSeeAll_movieFrag)
        val topRated_MovieSeeAll_movieFrag = view.findViewById<TextView>(R.id.topRated_MovieSeeAll_movieFrag)

        hideLayout()

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.refresh()
        movieViewModel.getPopularMovies("",1)

        observeViewModel()

        popular_MovieSeeAll_movieFrag.setOnClickListener {
            val intent = Intent(context, SeeAllMovieActivity::class.java)
            intent.putExtra("ComeFrom","PopularMovies")
            startActivity(intent)
        }

        topRated_MovieSeeAll_movieFrag.setOnClickListener {
            val intent = Intent(context, SeeAllMovieActivity::class.java)
            intent.putExtra("ComeFrom","TopRatedMovies")
            startActivity(intent)
        }

        showLayout()

        return view
    }

    private fun hideLayout() {

        popular_MovieLayout_movieFrag.visibility = View.GONE
        topRated_MovieLayout_movieFrag.visibility = View.GONE
    }

    private fun showLayout(){

        popular_MovieLayout_movieFrag.visibility = View.VISIBLE
        topRated_MovieLayout_movieFrag.visibility = View.VISIBLE
    }

    private fun observeViewModel() {

        movieViewModel.PopularMovies.observe(requireActivity(), Observer {countries ->
            countries?.let {

                popularMovieAdapter = PopularMovieAdapter(requireActivity(),it)
                popularMovieRecView_moviesFragment.apply {
                    adapter = popularMovieAdapter
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    setHasFixedSize(false)

                }

            }

        })

        movieViewModel.TopRatedMovies.observe(requireActivity(), Observer {countries ->
            countries?.let {

                topRatedMovieAdapter = PopularMovieAdapter(requireActivity(),it)
                topRatedMovieRecView_moviesFragment.apply {
                    adapter = topRatedMovieAdapter
                    layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                    setHasFixedSize(false)
                }

            }

        })

        movieViewModel.movieLoadError.observe(requireActivity(), Observer { isError ->


            if(isError == "" || isError == null){
                popular_MovieLayout_movieFrag.visibility = View.VISIBLE
                topRated_MovieLayout_movieFrag.visibility = View.VISIBLE
            }else{
                popular_MovieLayout_movieFrag.visibility = View.INVISIBLE
                topRated_MovieLayout_movieFrag.visibility = View.INVISIBLE
            }

        })
        movieViewModel.loading.observe(requireActivity(), Observer { isLoading ->
            isLoading?.let {

                if(it) {
                    popular_MovieLayout_movieFrag.visibility = View.GONE
                    topRated_MovieLayout_movieFrag.visibility = View.GONE
                }

            }
        })
    }


}