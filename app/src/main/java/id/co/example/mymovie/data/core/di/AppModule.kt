package id.co.example.mymovie.data.core.di

import id.co.example.mymovie.MovieViewModel
import id.co.example.mymovie.domain.useCase.MovieNowPlayingInteractor
import id.co.example.mymovie.domain.useCase.MovieNowPlayingUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieNowPlayingUseCase> { MovieNowPlayingInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}