package id.co.example.mymovie.core.data.core.di

import id.co.example.mymovie.core.domain.useCase.MovieInteractor
import id.co.example.mymovie.core.domain.useCase.MovieUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    single<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
}