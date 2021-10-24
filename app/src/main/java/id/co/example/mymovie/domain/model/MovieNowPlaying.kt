package id.co.example.mymovie.domain.model

data class MovieNowPlaying (
    val overview: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val popularity: Double? = 0.0,
    val voteAverage: Int? = 0,
    val id: Int? = 0,
    val voteCount: Int? = 0,
    val favorite: Boolean? = false
)