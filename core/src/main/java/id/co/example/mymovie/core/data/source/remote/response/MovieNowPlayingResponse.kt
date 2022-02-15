package id.co.example.mymovie.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieNowPlayingResponse(

	@field:SerializedName("results")
	val results: List<MovieNowPlayingItem>

)

