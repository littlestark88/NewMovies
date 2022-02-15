package id.co.example.mymovie.core.data.source.local


import id.co.example.mymovie.core.data.source.local.dao.MovieDao
import id.co.example.mymovie.core.data.source.local.entity.MovieNowPlayingEntity
import id.co.example.mymovie.core.data.source.local.entity.TvPopularEntity
import io.reactivex.Flowable

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovieNowPlaying(): Flowable<List<MovieNowPlayingEntity>> = movieDao.getAllMovieNowPlaying()

    fun insertMovieNowPlaying(movieNowPlayingEntity: List<MovieNowPlayingEntity>) = movieDao.insertMovieNowPlaying(movieNowPlayingEntity)

    fun getFavoriteMovieNowPlaying(): Flowable<List<MovieNowPlayingEntity>> = movieDao.getFavoriteMovie()

    fun updateMovieNowPlaying(movieNowPlayingEntity: MovieNowPlayingEntity) = movieDao.updateMovieNowPlaying(movieNowPlayingEntity)

    fun deleteMovieNowPlaying(movieNowPlayingEntity: MovieNowPlayingEntity) = movieDao.deleteMovieNowPlaying(movieNowPlayingEntity)

    fun setFavoriteMovie(movie: MovieNowPlayingEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateMovieNowPlaying(movie)
    }

    fun getAllTvPopular(): Flowable<List<TvPopularEntity>> = movieDao.getAllTvPopular()

    fun insertTvPopular(tvPopularEntity: List<TvPopularEntity>) = movieDao.insertTvPopular(tvPopularEntity)

    fun getFavoriteTvPopular(): Flowable<List<TvPopularEntity>> = movieDao.getFavoriteTv()

    fun updateTvPopular(tvPopularEntity: TvPopularEntity) = movieDao.updateTvPopular(tvPopularEntity)

    fun deleteTvPopular(tvPopularEntity: TvPopularEntity) = movieDao.deleteTvPopular(tvPopularEntity)

    fun setFavoriteTv(tv: TvPopularEntity, newState: Boolean) {
        tv.isFavorite = newState
        movieDao.updateTvPopular(tv)
    }
}