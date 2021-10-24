package id.co.example.mymovie.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieNowPlayingResponse(

	@field:SerializedName("results")
	val results: List<MovieNowPlayingItem>

)

