package id.co.example.mymovie.domain.repository

import id.co.example.mymovie.data.source.Resource
import id.co.example.mymovie.domain.model.MovieNowPlaying
import io.reactivex.Flowable

interface IMovieNowPlayingRepository {

    fun getAllNowPlaying(): Flowable<Resource<List<MovieNowPlaying>>>

}