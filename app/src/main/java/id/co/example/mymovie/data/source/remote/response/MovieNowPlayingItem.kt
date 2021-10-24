package id.co.example.mymovie.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieNowPlayingItem(

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

    @field:SerializedName("original_title")
    val originalTitle: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

    @field:SerializedName("popularity")
    val popularity: Double? = 0.0,

    @field:SerializedName("vote_average")
    val voteAverage: Int? = 0,

    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("vote_count")
    val voteCount: Int? = 0
)
