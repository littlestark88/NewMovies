package id.co.example.core.domain.useCase

import id.co.example.core.data.source.Resource
import id.co.example.core.domain.model.MovieNowPlaying
import id.co.example.core.domain.model.TvPopular
import io.reactivex.Flowable

interface MovieUseCase {
    fun getAllNowPlaying(): Flowable<Resource<List<MovieNowPlaying>>>
    fun getFavoriteMovie(): Flowable<List<MovieNowPlaying>>
    fun setFavoriteMovie(movieNowPlaying: MovieNowPlaying, state: Boolean)

    fun getAllTvPopular(): Flowable<Resource<List<TvPopular>>>
    fun getFavoriteTv(): Flowable<List<TvPopular>>
    fun setFavoriteTv(tvPopular: TvPopular, state: Boolean)
}