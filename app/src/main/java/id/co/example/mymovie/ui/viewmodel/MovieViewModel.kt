package id.co.example.mymovie.ui.viewmodel



import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import id.co.example.mymovie.core.domain.model.MovieNowPlaying
import id.co.example.mymovie.core.domain.model.TvPopular
import id.co.example.mymovie.core.domain.useCase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
    val movieNowPlaying = LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllNowPlaying())
    val tvPopular = LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllTvPopular())
    fun setFavoriteMovieNowPlaying(movieNowPlaying: MovieNowPlaying, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movieNowPlaying, newStatus)
    fun setFavoriteTvPopular(tvPopular: TvPopular, newStatus: Boolean) =
        movieUseCase.setFavoriteTv(tvPopular, newStatus)
}