package iammert.com.androidarchitecture.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import iammert.com.androidarchitecture.ui.detail.MovieDetailViewModel
import iammert.com.androidarchitecture.ui.main.MovieListViewModel
import iammert.com.androidarchitecture.viewmodel.MovieViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindsMovieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindsMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    abstract fun bindsViewModelFactory(movieViewModelFactory: MovieViewModelFactory): ViewModelProvider.Factory
}