package id.co.example.mymovie.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.example.mymovie.core.data.core.helper.Constant
import id.co.example.mymovie.core.data.core.helper.Constant.EXTRA_DATA
import id.co.example.mymovie.core.data.core.helper.Constant.MOVIE
import id.co.example.mymovie.core.data.core.helper.Constant.TYPE
import id.co.example.mymovie.favorite.databinding.FragmentMovieFavoriteBinding
import id.co.example.mymovie.favorite.viewmodel.FavoriteViewModel
import id.co.example.mymovie.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class MovieFavoriteFragment(private val movie: Boolean) : Fragment() {

    private var _binding: FragmentMovieFavoriteBinding? = null
    private val binding get() = _binding
    private val movieViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(movie) {
            val favoriteAdapter = FavoriteMovieAdapter()
            favoriteAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(EXTRA_DATA, it)
                intent.putExtra(TYPE, MOVIE)
                startActivity(intent)
            }

            movieViewModel.getFavoriteMovie.observe(viewLifecycleOwner, {
                favoriteAdapter.setData(it)
            })
            with(binding?.rvMovieFavorite){
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = favoriteAdapter
            }
        } else {
            val favoriteTvAdapter = FavoriteTvAdapter()
            favoriteTvAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(EXTRA_DATA, it)
                intent.putExtra(TYPE, Constant.TV)
                startActivity(intent)
            }

            movieViewModel.getFavoriteTv.observe(viewLifecycleOwner, {
                favoriteTvAdapter.setData(it)
            })
            with(binding?.rvMovieFavorite) {
                this?.layoutManager = LinearLayoutManager(context)
                this?.setHasFixedSize(true)
                this?.adapter = favoriteTvAdapter
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}