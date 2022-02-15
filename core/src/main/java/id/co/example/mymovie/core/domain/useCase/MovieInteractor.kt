package id.co.example.mymovie.core.domain.useCase

import id.co.example.mymovie.core.domain.model.MovieNowPlaying
import id.co.example.mymovie.core.domain.model.TvPopular
import id.co.example.mymovie.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getAllNowPlaying() = movieRepository.getAllNowPlaying()
    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()
    override fun setFavoriteMovie(movieNowPlaying: MovieNowPlaying, state: Boolean) =
        movieRepository.setFavoriteMovie(movieNowPlaying, state)


    override fun getAllTvPopular() = movieRepository.getAllTvPopulate()
    override fun getFavoriteTv() = movieRepository.getFavoriteTv()

    override fun setFavoriteTv(tvPopular: TvPopular, state: Boolean) =
        movieRepository.setFavoriteTv(tvPopular, state)
}