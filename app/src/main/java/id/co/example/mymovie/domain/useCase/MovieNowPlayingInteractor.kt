package id.co.example.mymovie.domain.useCase

import id.co.example.mymovie.domain.repository.IMovieNowPlayingRepository

class MovieNowPlayingInteractor(private val movieNowPlayingRepository: IMovieNowPlayingRepository): MovieNowPlayingUseCase {

    override fun getAllNowPlaying() = movieNowPlayingRepository.getAllNowPlaying()
}