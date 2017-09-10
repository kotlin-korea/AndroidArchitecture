package iammert.com.androidarchitecture.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iammert.com.androidarchitecture.ui.main.MovieListFragment

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment
}