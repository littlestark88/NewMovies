package id.co.example.mymovie.data.source.local.dao

import androidx.room.*
import id.co.example.mymovie.data.source.local.entity.MovieNowPlayingEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieNowPlayingDao {

    @Query("SELECT * FROM movie_now_playing")
    fun getAllMovieNowPlaying(): Flowable<List<MovieNowPlayingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieNowPlaying(movieNowPlaying: List<MovieNowPlayingEntity>): Completable
    
    @Update
    fun updateMovieNowPlaying(movieNowPlaying: MovieNowPlayingEntity): Completable
}