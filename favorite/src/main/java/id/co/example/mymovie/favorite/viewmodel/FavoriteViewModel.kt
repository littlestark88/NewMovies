package id.co.example.mymovie.favorite.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import id.co.example.mymovie.core.domain.useCase.MovieUseCase

class FavoriteViewModel(movieUseCase: MovieUseCase): ViewModel() {
    val getFavoriteMovie = LiveDataReactiveStreams.fromPublisher(movieUseCase.getFavoriteMovie())
    val getFavoriteTv = LiveDataReactiveStreams.fromPublisher(movieUseCase.getFavoriteTv())

}