package id.co.example.mymovie.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import id.co.example.mymovie.core.data.core.helper.Constant.API_KEY
import id.co.example.mymovie.core.data.core.helper.Constant.MOVIE
import id.co.example.mymovie.core.data.core.helper.Constant.TV
import id.co.example.mymovie.core.data.source.remote.network.ApiService
import id.co.example.mymovie.core.data.source.remote.response.ApiResponse
import id.co.example.mymovie.core.data.source.remote.response.MovieNowPlayingItem
import id.co.example.mymovie.core.data.source.remote.response.TvPopularItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource (private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getNowPlayingMovie(): Flowable<ApiResponse<List<MovieNowPlayingItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<MovieNowPlayingItem>>>()

        val client = apiService.getMovieNowPlaying(MOVIE,API_KEY)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(
                    (if (dataArray.isNotEmpty())
                        ApiResponse.Success(dataArray)
                    else ApiResponse.Empty)
                )
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getTvPopular(): Flowable<ApiResponse<List<TvPopularItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<TvPopularItem>>>()

        val client = apiService.getTvPopular(TV,API_KEY)
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.results
                resultData.onNext(
                    (if (dataArray.isNotEmpty())
                        ApiResponse.Success(dataArray)
                    else ApiResponse.Empty)
                )
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}