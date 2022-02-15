package id.co.example.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_now_playing")
data class MovieNowPlayingEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "popularity")
    var popularity: Double = 0.0,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.0,

    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "original_language")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "original_title")
    var originalTitle: String? = null,

    @ColumnInfo(name = "backdrop_path")
    var backDropPath: String? = null,

    @ColumnInfo(name = "release_date")
    var releaseDate: String? = null,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int = 0,

    @ColumnInfo(name = "isFavorite")
    var isFavorite:Boolean = false
)