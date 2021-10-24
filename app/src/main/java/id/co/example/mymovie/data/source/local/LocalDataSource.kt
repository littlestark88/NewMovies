package id.co.example.mymovie.data.source.local

import id.co.example.mymovie.data.source.local.dao.MovieNowPlayingDao
import id.co.example.mymovie.data.source.local.entity.MovieNowPlayingEntity
import io.reactivex.Flowable

class LocalDataSource(private val movieNowPlayingDao: MovieNowPlayingDao) {

    fun getAllMovieNowPlaying(): Flowable<List<MovieNowPlayingEntity>> = movieNowPlayingDao.getAllMovieNowPlaying()

    fun insertMovieNowPlaying(movieNowPlayingEntity: List<MovieNowPlayingEntity>) = movieNowPlayingDao.insertMovieNowPlaying(movieNowPlayingEntity)

    fun updateMovieNowPlaying(movieNowPlayingEntity: MovieNowPlayingEntity) = movieNowPlayingDao.updateMovieNowPlaying(movieNowPlayingEntity)
}