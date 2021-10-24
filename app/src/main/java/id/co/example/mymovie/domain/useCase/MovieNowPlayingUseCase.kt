package id.co.example.mymovie.domain.useCase

import id.co.example.mymovie.data.source.Resource
import id.co.example.mymovie.domain.model.MovieNowPlaying
import io.reactivex.Flowable

interface MovieNowPlayingUseCase {
    fun getAllNowPlaying(): Flowable<Resource<List<MovieNowPlaying>>>
}