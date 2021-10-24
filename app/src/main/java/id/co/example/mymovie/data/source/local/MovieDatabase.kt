package id.co.example.mymovie.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.co.example.mymovie.data.source.local.dao.MovieNowPlayingDao
import id.co.example.mymovie.data.source.local.entity.MovieNowPlayingEntity

@Database(entities = [MovieNowPlayingEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieNowPlayingDao(): MovieNowPlayingDao
}