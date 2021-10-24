package id.co.example.mymovie

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import id.co.example.mymovie.domain.useCase.MovieNowPlayingUseCase

class MovieViewModel(movieNowPlayingUseCase: MovieNowPlayingUseCase): ViewModel() {
    val movieNowPlaying = LiveDataReactiveStreams.fromPublisher(movieNowPlayingUseCase.getAllNowPlaying())
}