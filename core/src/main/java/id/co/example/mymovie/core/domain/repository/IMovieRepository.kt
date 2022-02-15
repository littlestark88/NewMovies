package id.co.example.mymovie.core.domain.repository

import id.co.example.mymovie.core.data.source.Resource
import id.co.example.mymovie.core.domain.model.MovieNowPlaying
import id.co.example.mymovie.core.domain.model.TvPopular
import io.reactivex.Flowable

interface IMovieRepository {

    fun getAllNowPlaying(): Flowable<Resource<List<MovieNowPlaying>>>

    fun getFavoriteMovie(): Flowable<List<MovieNowPlaying>>

    fun setFavoriteMovie(movie: MovieNowPlaying, state: Boolean)

    fun getAllTvPopulate(): Flowable<Resource<List<TvPopular>>>

    fun getFavoriteTv(): Flowable<List<TvPopular>>

    fun setFavoriteTv(tv: TvPopular, state: Boolean)

}