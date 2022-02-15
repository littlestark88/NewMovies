package id.co.example.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.co.example.core.data.source.local.dao.MovieDao
import id.co.example.core.data.source.local.entity.MovieNowPlayingEntity
import id.co.example.core.data.source.local.entity.TvPopularEntity

@Database(entities = [MovieNowPlayingEntity::class, TvPopularEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieNowPlayingDao(): MovieDao
}