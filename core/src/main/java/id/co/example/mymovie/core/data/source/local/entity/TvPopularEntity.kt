package id.co.example.mymovie.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_populate")
data class TvPopularEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int? = 0,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "originalLanguage")
    var originalLanguage: String? = null,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "backDropPath")
    var backDropPath: String? = null,

    @ColumnInfo(name = "originalName")
    var originalName: String? = null,

    @ColumnInfo(name = "popularity")
    var popularity: Double? = 0.0,

    @ColumnInfo(name = "voteAverage")
    var voteAverage: Double? = 0.0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "voteCount")
    var voteCount: Int? = 0,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
