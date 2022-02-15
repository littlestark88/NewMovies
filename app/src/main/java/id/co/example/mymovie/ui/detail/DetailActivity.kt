package id.co.example.mymovie.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import id.co.example.mymovie.R
import id.co.example.mymovie.core.data.core.helper.Constant.EXTRA_DATA
import id.co.example.mymovie.core.data.core.helper.Constant.MOVIE
import id.co.example.mymovie.core.data.core.helper.Constant.POSTER_URL
import id.co.example.mymovie.core.data.core.helper.Constant.TV
import id.co.example.mymovie.core.data.core.helper.Constant.TYPE
import id.co.example.mymovie.core.domain.model.MovieNowPlaying
import id.co.example.mymovie.core.domain.model.TvPopular
import id.co.example.mymovie.databinding.ActivityDetailBinding
import id.co.example.mymovie.ui.viewmodel.MovieViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(){

    private lateinit var binding: ActivityDetailBinding
    private lateinit var type: String
    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(EXTRA_DATA) && intent.hasExtra(TYPE)) {
            when (intent.getStringExtra(TYPE)) {
                MOVIE -> {
                    type = MOVIE
                    val dataMovie = intent.getParcelableExtra<MovieNowPlaying>(EXTRA_DATA)
                    dataMovie?.let {
                        setStatusFavorite(it.favorite)
                        initDetailMovie(it)
                    }
                }
                TV -> {
                    type = TV
                    val dataTV = intent.getParcelableExtra<TvPopular>(EXTRA_DATA)

                    dataTV?.let {
                        setStatusFavorite(it.favorite)
                        initDetailTv(it)
                    }
                }
            }
        }
    }

    private fun initDetailMovie(data: MovieNowPlaying?) {
        with(binding) {
            tvTitle.text = data?.title
            tvOverviewDesc.text = data?.overview
            tvRating.text = data?.voteAverage.toString()
            Picasso
                .get()
                .load(POSTER_URL + data?.posterPath)
                .placeholder(R.drawable.ic_refresh)
                .into(imgPoster)
            Picasso
                .get()
                .load(POSTER_URL + data?.backdropPath)
                .placeholder(R.drawable.ic_refresh)
                .into(imgBackImage)
            cvFavorite.setOnClickListener {
                setFavoriteMovie(data)
            }
        }

    }

    private fun initDetailTv(data: TvPopular?) {
        with(binding) {
            tvTitle.text = data?.name
            tvOverviewDesc.text = data?.overview
            tvRating.text = data?.voteAverage.toString()
            Picasso
                .get()
                .load(POSTER_URL + data?.posterPath)
                .placeholder(R.drawable.ic_refresh)
                .into(imgPoster)
            Picasso
                .get()
                .load(POSTER_URL + data?.backdropPath)
                .placeholder(R.drawable.ic_refresh)
                .into(imgBackImage)
            cvFavorite.setOnClickListener {
                setFavoriteTv(data)
            }
        }
    }

    private fun setFavoriteMovie(dataMovie: MovieNowPlaying?) {
        dataMovie?.let {
            var favoriteStateMovie = dataMovie.favorite
            favoriteStateMovie = !favoriteStateMovie
            movieViewModel.setFavoriteMovieNowPlaying(dataMovie, favoriteStateMovie)
            setStatusFavorite(favoriteStateMovie)
        }

    }

    private fun setFavoriteTv(dataTV: TvPopular?) {
        dataTV?.let {
            var favoriteStateTv = dataTV.favorite
            favoriteStateTv = !favoriteStateTv
            movieViewModel.setFavoriteTvPopular(dataTV, favoriteStateTv)
            setStatusFavorite(favoriteStateTv)
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.imgFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_unfavorite
                )
            )
        }
    }
}