package id.co.example.mymovie.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvPopular(
    val firstAirDate: String? = null,
    val overview: String? = null,
    val originalLanguage: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val originalName: String? = null,
    val popularity: Double? = 0.0,
    val voteAverage: Double? = 0.0,
    val name: String? = null,
    val id: Int? = 0,
    val voteCount: Int? = null,
    val favorite: Boolean = false
): Parcelable
