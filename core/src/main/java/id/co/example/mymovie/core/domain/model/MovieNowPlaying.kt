package id.co.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieNowPlaying (
    val overview: String,
    val originalLanguage: String,
    val originalTitle: String,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
    val id: Int,
    val voteCount: Int,
    val favorite: Boolean = false
): Parcelable