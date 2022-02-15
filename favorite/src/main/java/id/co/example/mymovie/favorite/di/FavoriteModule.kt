package id.co.example.mymovie.favorite.di

import id.co.example.mymovie.favorite.viewmodel.FavoriteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel {
        FavoriteViewModel(get())
    }
}