package id.co.example.core.data.source.remote.network

import id.co.example.core.data.source.remote.response.MovieNowPlayingResponse
import id.co.example.core.data.source.remote.response.TvPopularResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{type}/now_playing")
    fun getMovieNowPlaying(
        @Path("type")type: String,
        @Query("api_key")apiKey: String
    ): Flowable<MovieNowPlayingResponse>

    @GET("{type}/popular")
    fun getTvPopular(
        @Path("type")type: String,
        @Query("api_key")apiKey: String
    ): Flowable<TvPopularResponse>

}