package iammert.com.androidarchitecture.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iammert.com.androidarchitecture.ui.detail.MovieDetailActivity
import iammert.com.androidarchitecture.ui.main.MainActivity

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilderModule::class))
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun movieDetailActivity(): MovieDetailActivity
}
