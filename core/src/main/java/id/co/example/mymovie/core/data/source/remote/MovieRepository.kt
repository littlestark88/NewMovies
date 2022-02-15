package id.co.example.mymovie.core.data.source.remote


import id.co.example.mymovie.core.data.core.helper.AppExecutors
import id.co.example.mymovie.core.data.core.helper.DataMapper
import id.co.example.mymovie.core.data.source.NetworkBoundResource
import id.co.example.mymovie.core.data.source.Resource
import id.co.example.mymovie.core.data.source.local.LocalDataSource
import id.co.example.mymovie.core.data.source.remote.response.ApiResponse
import id.co.example.mymovie.core.data.source.remote.response.MovieNowPlayingItem
import id.co.example.mymovie.core.data.source.remote.response.TvPopularItem
import id.co.example.mymovie.core.domain.model.MovieNowPlaying
import id.co.example.mymovie.core.domain.model.TvPopular
import id.co.example.mymovie.core.domain.repository.IMovieRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getAllNowPlaying(): Flowable<Resource<List<MovieNowPlaying>>> =
        object : NetworkBoundResource<List<MovieNowPlaying>, List<MovieNowPlayingItem>>() {

            override fun loadFromDB(): Flowable<List<MovieNowPlaying>> {
                return localDataSource.getAllMovieNowPlaying().map {
                    DataMapper.mapMovieNowPlayingEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<MovieNowPlaying>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieNowPlayingItem>>> =
                remoteDataSource.getNowPlayingMovie()

            override fun saveCallResult(data: List<MovieNowPlayingItem>) {
                val movieNowPlayingList = DataMapper.mapMovieNowPlayingResponseToEntities(data)
                localDataSource.insertMovieNowPlaying(movieNowPlayingList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteMovie(): Flowable<List<MovieNowPlaying>> {
        return localDataSource.getFavoriteMovieNowPlaying().map { DataMapper.mapMovieNowPlayingEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: MovieNowPlaying, state: Boolean) {
        val movieNowPlayingEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieNowPlayingEntity, state)}
    }

    override fun getAllTvPopulate(): Flowable<Resource<List<TvPopular>>> =
        object : NetworkBoundResource<List<TvPopular>, List<TvPopularItem>>() {

            override fun loadFromDB(): Flowable<List<TvPopular>> {
                return localDataSource.getAllTvPopular().map {
                    DataMapper.mapTvPopulateEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvPopular>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<TvPopularItem>>> =
                remoteDataSource.getTvPopular()

            override fun saveCallResult(data: List<TvPopularItem>) {
                val tvPopularList = DataMapper.mapTvPopularResponseToEntities(data)
                localDataSource.insertTvPopular(tvPopularList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteTv(): Flowable<List<TvPopular>> {
        return localDataSource.getFavoriteTvPopular().map { DataMapper.mapTvPopulateEntitiesToDomain(it) }
    }

    override fun setFavoriteTv(tv: TvPopular, state: Boolean) {
        val tvPopularEntity = DataMapper.mapDomainToEntityTv(tv)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTv(tvPopularEntity, state) }
    }


}