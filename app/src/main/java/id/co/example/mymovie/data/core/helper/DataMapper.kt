package id.co.example.mymovie.data.core.helper

import id.co.example.mymovie.data.source.local.entity.MovieNowPlayingEntity
import id.co.example.mymovie.data.source.remote.response.MovieNowPlayingItem
import id.co.example.mymovie.domain.model.MovieNowPlaying

object DataMapper {

    fun mapResponseToEntities(input: List<MovieNowPlayingItem>): List<MovieNowPlayingEntity> {
        val movieNowPlayingList = ArrayList<MovieNowPlayingEntity>()
        input.map {
            val movieNowPlaying = MovieNowPlayingEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                backDropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount,
                favorite = false
            )
            movieNowPlayingList.add(movieNowPlaying)
        }
        return movieNowPlayingList
    }

    fun mapEntitiesToDomain(input: List<MovieNowPlayingEntity>): List<MovieNowPlaying> =
        input.map {
            MovieNowPlaying(
                overview = it.overview.toString(),
                originalLanguage = it.originalLanguage.toString(),
                originalTitle = it.originalTitle.toString(),
                title = it.title.toString(),
                posterPath = it.posterPath.toString(),
                backdropPath = it.backDropPath.toString(),
                releaseDate = it.releaseDate.toString(),
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.movieId,
                voteCount = it.voteCount,
                favorite = it.favorite

            )
        }

    fun mapDomainToEntity(input: MovieNowPlaying) = MovieNowPlayingEntity(
        movieId = input.id,
        title = input.title,
        overview = input.overview,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        posterPath = input.posterPath,
        originalLanguage = input.originalLanguage,
        originalTitle = input.originalTitle,
        backDropPath = input.backdropPath,
        releaseDate = input.releaseDate,
        voteCount = input.voteCount,
        favorite = input.favorite
    )
}