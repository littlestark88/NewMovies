package id.co.example.mymovie.data.source.remote

import id.co.example.mymovie.data.core.helper.DataMapper
import id.co.example.mymovie.data.source.NetworkBoundResource
import id.co.example.mymovie.data.source.Resource
import id.co.example.mymovie.data.source.local.LocalDataSource
import id.co.example.mymovie.data.source.remote.response.ApiResponse
import id.co.example.mymovie.data.source.remote.response.MovieNowPlayingItem
import id.co.example.mymovie.domain.model.MovieNowPlaying
import id.co.example.mymovie.domain.repository.IMovieNowPlayingRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieNowPlayingRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
//    private val appExecutors: AppExecutors
): IMovieNowPlayingRepository {

    override fun getAllNowPlaying(): Flowable<Resource<List<MovieNowPlaying>>> =
        object : NetworkBoundResource<List<MovieNowPlaying>, List<MovieNowPlayingItem>>() {

            override fun loadFromDB(): Flowable<List<MovieNowPlaying>> {
                return localDataSource.getAllMovieNowPlaying().map {
                    DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<MovieNowPlaying>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<MovieNowPlayingItem>>> =
                remoteDataSource.getNowPlayingMovie()

            override fun saveCallResult(data: List<MovieNowPlayingItem>) {
                val movieNowPlayingList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertMovieNowPlaying(movieNowPlayingList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()


}