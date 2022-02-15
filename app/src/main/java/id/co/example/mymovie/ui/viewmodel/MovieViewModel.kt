package id.co.example.mymovie

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import id.co.example.mymovie.domain.useCase.MovieUseCase

class MovieViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val movieNowPlaying = LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllNowPlaying())
    val tvPopular = LiveDataReactiveStreams.fromPublisher(movieUseCase.getAllTvPopular())
}