package id.co.example.mymovie.core.data.source.local.dao

import androidx.room.*
import id.co.example.mymovie.core.data.source.local.entity.MovieNowPlayingEntity
import id.co.example.mymovie.core.data.source.local.entity.TvPopularEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_now_playing")
    fun getAllMovieNowPlaying(): Flowable<List<MovieNowPlayingEntity>>

    @Query("SELECT * FROM movie_now_playing where isFavorite = 1")
    fun getFavoriteMovie(): Flowable<List<MovieNowPlayingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieNowPlaying(movieNowPlaying: List<MovieNowPlayingEntity>): Completable
    
    @Update
    fun updateMovieNowPlaying(movieNowPlaying: MovieNowPlayingEntity)

    @Delete
    fun deleteMovieNowPlaying(movieNowPlaying: MovieNowPlayingEntity): Completable

    @Query("SELECT * FROM tv_populate")
    fun getAllTvPopular(): Flowable<List<TvPopularEntity>>

    @Query("SELECT * FROM tv_populate where isFavorite = 1")
    fun getFavoriteTv(): Flowable<List<TvPopularEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvPopular(tvPopular: List<TvPopularEntity>): Completable

    @Update
    fun updateTvPopular(tvPopular: TvPopularEntity)

    @Delete
    fun deleteTvPopular(tvPopular: TvPopularEntity): Completable
}