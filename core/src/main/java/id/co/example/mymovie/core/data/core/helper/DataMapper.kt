package id.co.example.core.data.core.helper

import id.co.example.core.data.source.local.entity.MovieNowPlayingEntity
import id.co.example.core.data.source.local.entity.TvPopularEntity
import id.co.example.core.data.source.remote.response.MovieNowPlayingItem
import id.co.example.core.data.source.remote.response.TvPopularItem
import id.co.example.core.domain.model.MovieNowPlaying
import id.co.example.core.domain.model.TvPopular

object DataMapper {

    fun mapMovieNowPlayingResponseToEntities(input: List<MovieNowPlayingItem>): List<MovieNowPlayingEntity> {
        val movieNowPlayingList = ArrayList<MovieNowPlayingEntity>()
        input.map {
            val movieNowPlaying = MovieNowPlayingEntity(
                movieId = it.id ?: 0,
                title = it.title,
                overview = it.overview,
                popularity = it.popularity ?: 0.0,
                voteAverage = it.voteAverage ?: 0.0,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                backDropPath = it.backdropPath,
                releaseDate = it.releaseDate,
                voteCount = it.voteCount ?: 0,
                isFavorite = false
            )
            movieNowPlayingList.add(movieNowPlaying)
        }
        return movieNowPlayingList
    }

    fun mapMovieNowPlayingEntitiesToDomain(input: List<MovieNowPlayingEntity>): List<MovieNowPlaying> =
        input.map {
            MovieNowPlaying(
                overview = it.overview.orEmpty(),
                originalLanguage = it.originalLanguage.orEmpty(),
                originalTitle = it.originalTitle.orEmpty(),
                title = it.title.orEmpty(),
                posterPath = it.posterPath.orEmpty(),
                backdropPath = it.backDropPath.orEmpty(),
                releaseDate = it.releaseDate.orEmpty(),
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                id = it.movieId,
                voteCount = it.voteCount,
                favorite = it.isFavorite

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
        isFavorite = input.favorite
    )

    fun mapDomainToEntityTv(input: TvPopular) = TvPopularEntity(
        tvId = input.id,
        firstAirDate = input.firstAirDate,
        overview = input.overview,
        originalLanguage = input.originalLanguage,
        posterPath = input.posterPath,
        backDropPath = input.backdropPath,
        originalName = input.originalName,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        name = input.name,
        voteCount = input.voteCount,
        isFavorite = input.favorite
    )

    fun mapTvPopularResponseToEntities(input: List<TvPopularItem>): List<TvPopularEntity> {
        val tvPopulateList = ArrayList<TvPopularEntity>()
        input.map {
            val tvPopulate = TvPopularEntity(
                tvId = it.id,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                backDropPath = it.backdropPath,
                originalName = it.originalName,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                name = it.name,
                voteCount = it.voteCount,
                isFavorite = false
            )
            tvPopulateList.add(tvPopulate)
        }
        return tvPopulateList
    }

    fun mapTvPopulateEntitiesToDomain(input: List<TvPopularEntity>): List<TvPopular> =
        input.map {
            TvPopular(
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                originalLanguage = it.originalLanguage,
                posterPath = it.posterPath,
                backdropPath = it.backDropPath,
                originalName = it.originalName,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                name = it.name,
                id = it.tvId,
                voteCount = it.voteCount,
                favorite = it.isFavorite
            )
        }
}