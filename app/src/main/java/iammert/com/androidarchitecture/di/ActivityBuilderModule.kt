package iammert.com.androidarchitecture.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import iammert.com.androidarchitecture.ui.detail.MovieDetailActivity
import iammert.com.androidarchitecture.ui.main.MainActivity

/**
 * Created by mertsimsek on 30/05/2017.
 */
@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilderModule::class))
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun movieDetailActivity(): MovieDetailActivity
}
